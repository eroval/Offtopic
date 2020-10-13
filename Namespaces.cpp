//People.h

#ifndef PEOPLE_H
#define PEOPLE_H

#include <iostream>
#include <string>

namespace People {
	
	class Person {
	private:
		std::string name;
		int age;

	public:
		~Person();
		Person();
		Person(std::string name, int age);
		Person(const Person& person);
		Person& operator=(const Person& person);

		void setAge(int age);
		void setName(std::string name);

		int getAge() const;
		std::string getName() const;

		void print();
	};

	class Student {
	private:
		std::string name;
		int age;
		float grade;
	public:
		~Student();
		Student();
		Student(std::string name, int age, float grade);
		Student(const Student& student);
		Student& operator=(const Student& student);

		void setAge(int age);
		void setName(std::string name);
		void setGrade(float grade);

		int getAge() const;
		std::string getName() const;
		float getGrade() const;

		void print();
	};
}
#endif


//People.cpp

#include "People.h";
using namespace People;

Person::~Person() {
	std::cout << "Person Destructor Called\n";
}

Person::Person() {
	this->name = "No name";
	this->age = 0;
}

Person::Person(std::string name, int age) {
	this->name = name;
	if (age < 0) {
		age = 0;
	}
	this->age = age;
}

Person::Person(const Person& obj) {
	this->name = obj.name;
	this->age = obj.age;
}

Person& Person::operator=(const Person& obj) {
	this->name = obj.name;
	this->age = obj.age;
	return *this;
}

void Person::setAge(int age) {
	if (age < 0) {
		age = 0;
	}
	this->age = age;
}

void Person::setName(std::string name) {
	this->name = name;
}

int Person::getAge() const{
	return this->age;
}

std::string Person::getName() const {
	return this->name;
}

void Person::print() {
	std::cout << "Name: " << this->name << "\nAge: " << this->age << "\n\n";
}





Student::~Student() {
	std::cout << "Student Destructor Called\n";
}

Student::Student() {
	this->name = "No name";
	this->age = 0;
	this->grade = 2;
}

Student::Student(std::string name, int age, float grade) {
	this->name = name;
	if (age < 0) {
		age = 0;
	}
	if (grade > 6 || grade < 2) {
		grade = 2;
	}
	this->age = age;
	this->grade = grade;
}

Student::Student(const Student& obj) {
	this->name = obj.name;
	this->age = obj.age;
	this->grade = obj.grade;
}

Student& Student::operator=(const Student& obj) {
	this->name = obj.name;
	this->age = obj.age;
	this->grade = obj.grade;
	return *this;
}

void Student::setAge(int age) {
	if (age < 0) {
		age = 0;
	}
	this->age = age;
}

void Student::setName(std::string name) {
	this->name = name;
}

void Student::setGrade(float grade) {
	if (grade > 6 || grade < 2) {
		grade = 2;
	}
	this->grade = grade;
}

int Student::getAge() const {
	return this->age;
}

std::string Student::getName() const {
	return this->name;
}

float Student::getGrade() const {
	return this->grade;
}


void Student::print() {
	std::cout << "Name: " << this->name << "\nAge: " << this->age << "\nGrade: "<<this->grade<<"\n\n";
}


//main.cpp

#include <iostream>
#include <chrono>
#include <memory>
#include "People.h"
using namespace std;


int main() {
	People::Person b("Alf", 35);
	People::Student a("Alfonso", 5, 10);
	b.print();
	a.print();
	return 0;
}