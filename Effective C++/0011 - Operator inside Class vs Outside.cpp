/*
#include <iostream>

template <typename DataType>
class Rational {
private:
	DataType num;
	DataType den;
public:
	Rational(DataType numerator = 1, DataType denominator = 1) :num(numerator), den(denominator) {
	}
	Rational(const Rational& object) :num(object.num), den(object.den) {
	}

	Rational& operator=(const Rational& object) {
		this->num = object.num;
		this->den = object.den;
		return *this;
	}

	DataType getNum() const {
		return num;
	}
	DataType getDen() const {
		return den;
	}


	std::ostream& out(std::ostream& output) const{
		output << this->num << "/" << this->den;
		return output;
	}

};

template <typename DataType>
std::ostream& operator<<(std::ostream& output, const Rational<DataType>& object) {
	return object.out(output);
}

template <typename DataType>
Rational<DataType> operator*(const Rational<DataType>& lhs, const Rational<DataType>& rhs) {
	return Rational(lhs.getNum() * rhs.getNum(), lhs.getDen() * rhs.getDen());
};

template <typename DataType>
Rational<DataType> operator*(const Rational<DataType>& lhs, int b) {
	return Rational(lhs.getNum() * b, lhs.getDen());
};

template <typename DataType>
Rational<DataType> operator*(int b, const Rational<DataType>& lhs) {
	return Rational(lhs.getNum() * b, lhs.getDen());
};

int main() {
	Rational<int> a(5, 7);
	Rational<int> b(2, 7);
	std::cout<< a * b << "\n";
	std::cout << (1/2) * a << "\n";
	return 0;
}
*/


#include <iostream>

class Rational {
private:
	int num;
	int den;
public:
	Rational(int numerator = 1, int denominator = 1) :num(numerator), den(denominator) {
	}
	Rational(const Rational& object) :num(object.num), den(object.den) {
	}

	Rational& operator=(const Rational& object) {
		this->num = object.num;
		this->den = object.den;
		return *this;
	}

	int getNum() const {
		return num;
	}
	int getDen() const {
		return den;
	}


	std::ostream& out(std::ostream& output) const {
		output << this->num << "/" << this->den;
		return output;
	}

};


Rational operator*(const Rational lhs, const Rational& rhs) {
	return Rational(lhs.getNum() * rhs.getNum(), lhs.getDen() * rhs.getDen());
};

std::ostream& operator<<(std::ostream& output, const Rational& object) {
	return object.out(output);
}

int main() {
	Rational a(5, 7);
	Rational b(2, 7);
	std::cout << a * b << "\n";
	std::cout << (1 / 2) * a << "\n";
	return 0;
}