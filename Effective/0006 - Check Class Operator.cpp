#include <iostream>
using namespace std;

class Guy {
private:
	int age;
public:
	Guy() :age(18) {}
	Guy(int age) : age(age) {}
	Guy(const Guy& object) :age(object.age) {}
	Guy& operator=(const Guy& object) {
		
		//  --------------------------- //
		//  Check if it's the same obj  //
		//  --------------------------- //

		if (this == &object) { return *this; }
		else {
			this->age = object.age;
		}
	}

	void setAge(int age){
		this->age = age;
	}

	int getAge() const {
		return this->age;
	}

};

int main() {
	Guy guy1;
	std::cout << guy1.getAge() << "\n";
}