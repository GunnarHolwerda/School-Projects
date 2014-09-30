/*
Gunnar Holwerda
lab 03
precedence.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main() {
	
	double m, v, c, r;					//Declare local variables
	
	printf("Please enter the values: \n");
	printf("m = ");
	scanf("%lf", &m);			//Get m
	printf("v = ");
	scanf("%lf", &v);			//Get v
	printf("c = ");
	scanf("%lf", &c);			//Get c
	
	r = m/sqrt((1 - ((v * v) / (c * c)))); //Calculate r
	
	printf("m = %.1f, v = %.1f, c = %.1f\n", m, v, c);		//Print out the variables
	printf("r = %.2f\n", r);			//Print out r
	
	return 0;
}
