//This shows when it is a good case to bass something not be reference but by value

#include <iostream>
using namespace std;

class Vector {
private:
	float x, y, z;
public:
	Vector();
	Vector(int x, int y, int z);
	Vector(const Vector&);
	~Vector();
	Vector& operator=(const Vector&);

	void print() const;

	/*
	inline const Vector operator*(const int);
	inline const Vector operator*(const int);
	inline const Vector operator*(const int);
	*/

	template <typename T, typename std::enable_if<std::is_arithmetic<T>::value>::type* = nullptr>
	inline const Vector operator*(T t) {
		return Vector(this->x * t, this->y * t, this->z * t);
	};

};

Vector::Vector() : x(0), y(0), z(0) {
}

Vector::Vector(int x, int y, int z) : x(x), y(y), z(z) {

}

Vector::Vector(const Vector& object) : x(object.x), y(object.y), z(object.z) {
}

Vector& Vector::operator=(const Vector& object) {
	this->x = object.x;
	this->y = object.y;
	this->z = object.z;
	return *this;
}

Vector::~Vector() {
	std::cout << "Vector Destructor Called...\n";
}

void Vector::print() const {
	std::cout << "x = " << this->x << "\ny = " << this->y << "\nz = " << this->z << "\n";
}

/*
inline const Vector Vector::operator*(const int value) {
	return Vector(this->x * value, this->y * value, this->z * value);
}
*/


int main() {
	Vector obj(1, 2, 3);
	obj.print();
	Vector newobj(obj * 2.25);
	newobj.print();
	return 0;
}