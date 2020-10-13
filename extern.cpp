//Variables.h

#ifndef VARIABLES_H
#define VARIABLES_H

#include <iostream>

extern float worldX;
extern float worldY;
extern float worldZ;


void ResetCoordinates();
void ChangeCoordinates(float x, float y, float z);
void PrintCoordinates();

#endif



//Variables.cpp

#include "Variables.h"

float worldX = 0;
float worldY = 0;
float worldZ = 0;

void ResetCoordinates() {
	worldX = 0;
	worldY = 0;
	worldZ = 0;
}

void ChangeCoordinates(float x, float y, float z) {
	worldX = x;
	worldY = y;
	worldZ = z;
}

void PrintCoordinates() {
	std::cout << "X = " << worldX << "\n"
		<< "Y = " << worldY << "\n"
		<< "Z = " << worldZ << "\n\n";
}



//main.cpp

#include <iostream>
#include <chrono>
#include <memory>
#include "People.h"
#include "Variables.h"
using namespace std;


int main() {
	PrintCoordinates();
	ChangeCoordinates(5.5f, 10.0f, 5.342f);
	PrintCoordinates();
	return 0;
}