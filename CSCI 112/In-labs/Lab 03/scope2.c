/*
Gunnar Holwerda
lab 03
scope2.c
*/

#include <stdio.h>
#include <stdlib.h>

float getNum();
int addRightToLeft();
float extraCreditTwo();

int main() {
	
	printf("Sum = %.1f\n", getNum() + getNum());
	
	return 0;
}

/*
	Name: getNum
	
	Function: gets a number from user input and then returns that number
*/
float getNum(){
	float x;
	
	printf("Please enter a rational number: ");
	scanf("%f", &x);
	
	return x;
}


//EXTRA CREDIT
/*
	Name: addRightToLeft
	
	Function: adds numbers using 5 +'s from right to left instead of left to right
*/
int addRightToLeft(){
	return (6 + (5 + (4 + (3 + (2 + 1)))));
}


/*
	Name: extraCreditTwo
	
	Function: evaluates an equation that has +, - , and * in reverse PEMDAS order
*/
float extraCreditTwo(){
	return (5 * (4 + (3 - 1)));
}