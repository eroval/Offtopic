#include <iostream>
#include <string>
using namespace std;


struct Vector3 {
	std::string id;
	float x, y, z;

	Vector3() {
		id = "Zero Vector";
		x = 0, y=0, z=0;
	};

	Vector3(std::string id, float x, float y, float z) {
		this->id = id;
		this->x = x;
		this->y = y;
		this->z = z;
	}

	ostream& out(ostream& a) const {
		a
			<< "id = " << id << "\n"
			<< "x = " << x << "\n"
			<< "y = " << y << "\n"
			<< "z = " << z << "\n";

		return a;
	}

	istream& ins(istream& a) {
		std::cout << "Enter id: ";
		getline(a, id);
		std::cout << "x = ";
		a >> x;
		std::cout << "y = ";
		a >> y;
		std::cout << "z = ";
		a >> z;
		return a;
	}
};

ostream& operator<<(ostream& out, const Vector3& a) {
	return a.out(out);
}

istream& operator>>(istream& in, Vector3& a) {
	return a.ins(in);
}

int main() {
	Vector3 a;
	std::cout << a << "\n";
	std::cin >> a;
	std::cout <<"\n" << a << "\n";
	Vector3 b("Vector2", 5, 5, 5);
	std::cout << b << "\n";
	return 0;
}