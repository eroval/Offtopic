/*
----------------------------Example----------------------------
Use objects to manage resources. Don't delete them yourself.
*/

class Investment { ... }; // root class of hierarchy of investment types

Investment* createInvestment();

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

