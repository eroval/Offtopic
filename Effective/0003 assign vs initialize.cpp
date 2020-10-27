#include <iostream>
#include <string>

class Cool {
private:
	std::string name;
	int age;
public:
	Cool();
	Cool(std::string name, int age);
	Cool(const Cool&);
	Cool& operator=(const Cool&);

	std::ostream& output(std::ostream& out) const;
	std::istream& input(std::istream& in);
};

//INPUT & OUTPUT
std::ostream& Cool::output(std::ostream& out) const {
	out << this->name
		<<"\n"<<age<<"\n";
	return out;
}

std::istream& Cool::input(std::istream& in) {
	std::getline(in, this->name);
	in >> age;
	return in;
}

std::ostream& operator<<(std::ostream& out, const Cool& object) {
	return object.output(out);
}

std::istream& operator>>(std::istream& in, Cool& object) {
	return object.input(in);
}
//END OF INPUT & OUTPUT

//Constructors and implemented functions:



Cool& Cool::operator=(const Cool& object) {
	this->name = object.name;
	return *this;
}


// --------------------------------- ASSIGN VS INITIALIZE ------------------------------ //
//add comments to either assign or initialize like:
/* 
ASSIGNMENTS/INITIALIZATIONS
...
*/
// ASSIGNMENTS

Cool::Cool(std::string name, int age) {
	this->name = name;
	this->age = age;
	std::cout << *this;
}


Cool::Cool() {
	this->name = "CoolGuy";
	this->age = 5;
	std::cout << *this;
}


Cool::Cool(const Cool& object) {
	this->name = object.name;
}


// INITIALIZATIONS
Cool::Cool(std::string name, int age) : name(name), age(age){
	std::cout << *this;
}

Cool::Cool() : name("CoolGuy"), age(5) {
	std::cout << *this;
}

Cool::Cool(const Cool& object) : name(object.name), age(object.age) {
	std::cout << *this;
}


// ------------------------------------------------------------------------------------- //


int main() {
	Cool a("looper", 10);
}