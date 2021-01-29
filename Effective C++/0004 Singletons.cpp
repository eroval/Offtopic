#include <iostream>


//Singleton Method 1 - through the use of a function and a local static instance



//Cool.h
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


Cool& obj() {
	static Cool ob;
	return ob;
}





// ------------------------------------------------------------------------------ //



//Singleton Method 2 - through the use of a static instance and private constructors(the better way):


//Cool.h
class Cool {

private:
	static Cool* instance;
	std::string name;
	int age;

	Cool() : name("CoolGuy"), age(18) {};


public:

	//Delete copy constructor
	Cool(const Cool&) = delete;

	//Instance get method
	static Cool* getInstance() {
		if (!instance)
			instance = new Cool;
		return instance;
	};


	//Getters:
	std::string getName() const {
		return this->name;	
	};
	int getAge() const {
		return this->age;
	};

	//Setters:
	void setName(std::string name) {
		this->name = name;
	}
	void setAge(int age) {
		this->age = age;
	}

	//Print
	void print() const {
		std::cout << "Name: " << this->name;
		std::cout << "\nAge: " << this->age << "\n";	
	}
};

Cool *Cool::instance = 0;


int main() {
	Cool *ptr = ptr->getInstance();
	std::cout << ptr->getAge() << "\n";
}



// ------------------------------------------------------------------------------ //



//Singleton Method 3 - through the use of a static instance and private constructors(the easiest and also good way):

class WorldVector {
public:
	WorldVector(const WorldVector&) = delete;
	static WorldVector& Get() {
		return instance;
	}

	static void print() {
		std::cout << "A WorldVector Class.\n"
			<< "x = " << x << "\ny = " << y << "\nz = " << z << "\n";
	}
private:
	static const int x = 0, y = 0, z = 0;
	//if not const gotta be initialized outside of class(static after all)
	WorldVector() {}
	static WorldVector instance;
};


//intitalize in a .cpp file(with static variables if not const)
WorldVector WorldVector::instance;
