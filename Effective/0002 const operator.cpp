#include <iostream>
#include <string>
#include <cassert>

class Cool {
private:
	std::string name;

public:
	Cool();
	Cool(std::string name);
	Cool(const Cool&);
	Cool& operator=(const Cool&);
	const char& operator[](size_t position) const;
	char& operator[](size_t position);

	std::ostream& output(std::ostream& out) const;
	std::istream& input(std::istream& in);
};

//INPUT & OUTPUT
std::ostream& Cool::output(std::ostream& out) const {
	out << this->name;
	return out;
}

std::istream& Cool::input(std::istream& in) {
	std::getline(in, this->name);
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
Cool::Cool() {
	this->name = "CoolGuy";
}


Cool::Cool(std::string name) {
	this->name = name;
}

Cool::Cool(const Cool& object) {
	this->name = object.name;
}


Cool& Cool::operator=(const Cool& object) {
	this->name = object.name;
	return *this;
}

//Two operators
const char& Cool::operator[](size_t position) const {
	return this->name[position];
}

//Casts
char& Cool::operator[](size_t position) {
	return	const_cast<char&>(static_cast<const Cool&>(*this)[position]);
}



//main
int main() {
	Cool obj;
	std::cout << obj << "\n";
	std::cin >> obj;
	std::cout << obj << "\n";
	std::cout << obj[2] << "\n";
	std::cin>>obj[20];
	std::cout << obj << "\n";
	return 0;
}