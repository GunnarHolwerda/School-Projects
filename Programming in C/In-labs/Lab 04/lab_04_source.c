/*
Gunnar Holwerda
Lab 04
*/

#include <stdio.h>
#include <stdlib.h>

int returnNumber();
void problem_1(int a, int b);
void problem_2(int a, int b);
void problem_3(int a, int b);
void problem_4(int input);
void problem_5(int a, int b);
void problem_6();

int main(){
	
	//problem_1(returnNumber(), returnNumber());
	//problem_2(returnNumber(), returnNumber());
	//problem_3(returnNumber(), returnNumber());
	//problem_4(returnNumber());
	//problem_5(returnNumber(), returnNumber());
	problem_6();
	
	return 0;
}

int returnNumber(){
	int input;
	
	printf("Please enter an integer: ");
	scanf("%d", &input);
	
	return input;
}

void problem_1(int a, int b){
	if (a && !b){
		printf("X.\n");
	}
	else{
		printf("Y.\n");
	}
}

void problem_2(int a, int b){
	if (!a && !b){
		printf("Y.\n");
	}
	else {
		printf("X.\n");
	}
}

void problem_3(int a, int b){
	if (a || b){
		printf("X.\n");
	}
	else{
		printf("Y.\n");
	}
}

void problem_4(int input){
	switch(input){
	case 0:
	case 1:
	case 2:
	case 3:
		printf("too low");
		break;
	case 4:
	case 5:
		printf("just right");
		break;
	case 6:
	case 7:
	case 8:
	case 9:
		printf("too high");
		break;
	default:
		printf("Sorry 0-9 is all I can do.");
	}
}

void problem_5(int a, int b){
	if ((a && b) || (!b && !a)){
		printf("X.\n");
	}
	else {
		printf("Y.\n");
	}
}

void problem_6(){
	float x = 1.1;
	if (x == (float)1.1) {
		printf("this should print.\n");
	}
}