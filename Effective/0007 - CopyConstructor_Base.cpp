/*
---------------------------Example---------------------------
We mustn't forget to use the base Copy constructor
otherwise not all data will be coppied.
*/


PriorityCustomer::PriorityCustomer(const PriorityCustomer& rhs)
: Customer(rhs), // invoke base class copy ctor
priority(rhs.priority)
{
logCall("PriorityCustomer copy constructor");
}
PriorityCustomer&
PriorityCustomer::operator=(const PriorityCustomer& rhs)
{
logCall("PriorityCustomer copy assignment operator");
Customer::operator=(rhs); // assign base class parts
priority = rhs.priority;
return *this;
}