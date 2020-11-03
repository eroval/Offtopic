/*
----------------------------Example----------------------------
Use objects to manage resources. Don't delete them yourself.
*/

class Investment { ... }; // root class of hierarchy of investment types

Investment* createInvestment();

void f()
{
	Investment* pInv = createInvestment(); // call factory function
	... // use pInv
		delete pInv; // release object
}
/*
This looks okay, but there are several ways f could fail to delete the
investment object it gets from createInvestment.There might be a premature
return statement somewhere inside the “...” part of the function.
If such a return were executed, control would never reach the
delete statement.A similar situation would arise if the uses of createInvestment
and delete were in a loop, and the loop was prematurely
exited by a break or goto statement.Finally, some statement inside the
“...” might throw an exception.If so, control would again not get to the
delete.Regardless of how the delete were to be skipped, we’d leak not
only the memory containing the investment object but also any
resources held by that object.
*/



//This function isn't that good because autoptr can be used only once
void f()
{
	std::auto_ptr<Investment> pInv(createInvestment());
	...
	...
}

//This one is a lot better because shared ptrs delete the object when NO
//pointer is pointing to it
void f()
{
		std::tr1::shared_ptr<Investment>
		pInv(createInvestment()); // call factory function
	... // use pInv as before
} // automatically delete
// pInv via shared_ptr’s dtor



/*
----------------------------Example 2----------------------------
*/

/*
Not all resources are heap - based, however,
and for such resources, smart pointers like auto_ptrand tr1::shared_ptr
are generally inappropriate as resource handlers.That being the case,
you’re likely to find yourself needing to create your own resourcemanaging
classes from time to time.
For example, suppose you’re using a C API to manipulate mutex
objects of type Mutex offering functions lockand unlock:
*/

void lock(Mutex* pm); // lock mutex pointed to by pm
void unlock(Mutex * pm); // unlock the mutex

/*
To make sure that you never forget to unlock a Mutex you’ve locked,
you’d like to create a class to manage locks.The basic structure of
such a class is dictated by the RAII principle that resources are
acquired during constructionand released during destruction :
*/


class Lock {
public:
	explicit Lock(Mutex* pm)
		: mutexPtr(pm)
	{
		lock(mutexPtr);
	} // acquire resource
	~Lock() { unlock(mutexPtr); } // release resource
private:
	Mutex* mutexPtr;
};

//Clients use Lock in the conventional RAII fashion :
Mutex m; // define the mutex you need to use
...
{ // create block to define critical section
	Lock ml(&m); // lock the mutex
	... // perform critical section operations
} // automatically unlock mutex at end of block
This is fine, but what should happen if a Lock object is copied ?
Lock ml1(&m); // lock m
Lock ml2(ml1); // copy ml1 to ml2 — what should happen here?

/*
Fortunately, tr1::shared_ptr allows specification of a “deleter” — a
function or function object to be called when the reference count
goes to zero. (This functionality does not exist for auto_ptr, which
always deletes its pointer.) The deleter is an optional second parameter
to the tr1::shared_ptr constructor, so the code would look
like this:
*/
class Lock {
public:
	explicit Lock(Mutex* pm) // init shared_ptr with the Mutex
		: mutexPtr(pm, unlock) // to point to and the unlock func
	{ // as the deleter†
		lock(mutexPtr.get()); // see Item15 for info on “get”
	}
private:
	std::tr1::shared_ptr<Mutex> mutexPtr; // use shared_ptr
}; // instead of raw pointer