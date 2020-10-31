/*

 --------------------------------------------------------
| This is NOT a good idea due to derived dynamic object  |
| always calling the base constructor                    |
 --------------------------------------------------------

*/
#include <iostream>
using namespace std;

class Element {
private:
	int x, y, z;
public:
	virtual void print();
	virtual ~Element();
	Element();
	Element(const Element&) = delete;
	Element& operator=(const Element&) = delete;
};

void Element::print() {
	std::cout << "print 1 called:\n";
	std::cout << "x = " << x << "\ny = " << y << "\nz = " << z << "\n\n";
}

Element::Element() : x(5), y(5), z(5) {
	print();
}

Element::~Element() {
	std::cout << "Destructor!\n";
}

class Point : public Element {
private:
	int m;
public:
	~Point();
	Point();

	void print();
};

Point::Point():m(10){
	print();
}

void Point::print() {
	std::cout << "print 2 called\n";
	std::cout << "m = " << m << "\n";
}

Point::~Point() {
	std::cout << "Point Destrcutor Called!\n";
}


Element* globalptr;


void showDestructor() {
	Element* ptr = new Point();
	globalptr = ptr;
}

void show() {
	showDestructor();
}

int main() 
{
	show();
	globalptr->print();
	return 0;
}