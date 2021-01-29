#include <iostream>
#include <cassert>
using namespace std;

class Guy {
private:
	int age;
	char* name;
public:
	Guy() : age(18), name(new char[5]{"Name"}) {}

	Guy(int age, const char* name) : age(age) 
	{ 
		this->setName(name);
	}

	Guy(const Guy& object) :age(object.age) {
		this->setName(object.name);
	}

	Guy& operator=(const Guy& object) {
		
		//  --------------------------- //
		//  Check if it's the same obj  //
		//  --------------------------- //
		if (this == &object) { return *this; }
		else {
			this->setAge(object.age);
			this->setName(object.name);
			return *this;
		}
	}
	

	void setAge(int age){
		this->age = age;
	}

	void setName(const char* name) {
		if (this->name != nullptr) {
			delete[] this->name;
		}
		this->name = new char[strlen(name)+1];
		for (int i = 0; i <= strlen(name); i++) {
			this->name[i] = name[i];
		}
		std::cout<<"Length = " << strlen(this->name)<<"\n";
	}

	int getAge() const {
		return this->age;
	}

	const char* getName() const {
		return this->name;
	}

	char operator[](int position) const{
		if (abs(position) < strlen(this->name)) {
			return this->name[position];
		}
		else throw std::out_of_range("Returning out of range element.");
	}

	char& operator[](int position) {
		if (abs(position) < strlen(this->name)) {
			return this->name[position];
		}
		else throw std::out_of_range("Assigning out of range element.");
	}
};
int main() {
	Guy guy1;
	std::cout << guy1.getName() << "\n";
	guy1.setName("Cooofghfghgfl");
	std::cout << guy1.getName() << "\n";	
	Guy guy2(18, "DenisimoTheBeastisimo");
	std::cout << guy2.getName() << "\n"<<guy2.getAge()<<"\n";
	guy1 = guy2;
	std::cout << guy1.getName() << "\n" << guy1.getAge() << "\n";
	Guy guy3(guy1);
	std::cout << guy3.getName() << "\n" << guy3.getAge() << "\n";
	Guy guy5(1, "Lool");
	guy3 = guy5;
	std::cout << guy3.getName() << "\n" << guy3.getAge() << "\n";
}
