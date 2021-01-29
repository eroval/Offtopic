#include <iostream>

//instead of
#define CALL_WITH_MAX(a, b) ((a) > (b) ? (a) : (b))

//use
template<typename T>
inline const T& callWithMax(const T& a, const T& b) 
{
	return a > b ? a : b;
}

int main() {
	int a = 5;
	int b = 0;
	std::cout << callWithMax(a, b) << "\n";
	std::cout << "a = " << a << "\n";
}