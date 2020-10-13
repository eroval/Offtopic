#include <iostream>
#include <chrono>
#include <memory>
using namespace std;


#define SWAP(a, b) { *a ^= *b; *b ^= *a; *a ^= *b; }

class AllocJanitor {
public:
	explicit AllocJanitor() 
	{
		mem::PushAllocator(context);
	}

	~AllocJanitor() 
	{
		mem::PopAllocator();
	}
};

class Foo {
private:
	int x;
public:
	Foo(int x) {
		this->x = x;
		this->print();
	}
	Foo() {
		this->x = 0;
		this->print();
	}

	void print() {
		std::cout << this->x << "\n\n";
	}
};

void Fact(int n) {
	int Sum = 2;
	for (int i = 3; i <= n; i++) {
		Sum *= i;
	}

	std::cout << Sum << "\n";
}

int Sum(int n) {
	return (n * (n + 1)) / 2;
}

int Pow(int n, int a) {
	int Sum = n;
	while (a > 1) {
		Sum *= n;
		a -= 1;
	}

	return Sum;
}

void swap(int& a, int& b) {
	a ^= b;
	b ^= a;
	a ^= b;
}

template <typename T> 
void swap2(T& a, T& b) {
	T c(a);
	a = b;
	b = c;
}

int main() {
	//Fact(5);
	//std::cout << Sum(10)<<"\n";
	//std::cout << Pow(5, 2)<<"\n";
	int a = 10;
	int b = 20;		
	
	auto start = std::chrono::steady_clock::now();


	auto end = std::chrono::steady_clock::now();
	std::cout << "Elapsed nanoseconds: " << std::chrono::duration_cast<std::chrono::nanoseconds> (end - start).count() << std::endl;

	return 0;
}