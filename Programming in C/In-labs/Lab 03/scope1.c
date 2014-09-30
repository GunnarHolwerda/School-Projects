/*
Gunnar Holwerda
Lab 03
scope1.c
*/

#include <stdio.h>
#include <stdlib.h>

//global variables
float numberOne;
float numberTwo;			

//function declarator
float sum();

int main() {	

	printf("Sum two rational numbers\nPlease enter a rational number: ");
	scanf("%f", &numberOne);
	printf("Please enter another rational number: ");
	scanf("%f", &numberTwo);
	
	printf("Number one = %.1f, Number two = %.1f\n", numberOne, numberTwo);
	
	printf("The sum is %.1f\n", sum());
	
	return 0;
}


/*
	Name: sum
	
	Function: returns the sum of two global variables numberOne and numberTwo
*/
	
float sum(){
	return numberOne + numberTwo;
}