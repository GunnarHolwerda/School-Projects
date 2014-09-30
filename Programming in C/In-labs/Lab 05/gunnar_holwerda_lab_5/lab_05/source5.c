/*
Gunnar Holwerda
Lab 5
2/11/14
*/

#include <stdio.h>
#include <stdlib.h>

void problem_1(char letter);
void problem_2();
void problem_3();
void extraCredit_1(int k);
void extraCredit_2AND(int inputOne, int inputTwo, int inputThree);
void extraCredit_2OR(int inputOne, int inputTwo, int inputThree);

int main(){
	
	//problem_1('c');
	problem_2();
	problem_3();
	extraCredit_1(3);
	extraCredit_2AND(0, 0, 0);
	extraCredit_2OR(0, 0, 0);

	return 0;
}

void problem_1(char letter){
	char input;
	while (scanf("%c", &input) != -1){
		if (letter == input){
			printf("The character %c has been found.\n", letter);
		}
	}
}

void problem_2(){
	int i = 3;
	int j = 0;
	
	j = i++;		//This is a statement C
	printf("the value of j is %i \n", j);
	
	j = --i;		//This is a statement B
	printf("the value of j is %i \n", j);
	
	j = i--;		//This is a statement D
	printf("the value of j is %i \n", j);
	
	j = ++i;		//This is a statement A
	printf("the value of j is %i \n", j);
}

void problem_3(){
	int count = 0;
	char input, previousInput;
	
	while(scanf("%c", &input) != -1){
		if (input == 'O'){
			count++;
		}
		else if (input == 'y' && previousInput == 'O'){
			printf("A group of %i capital letter O's have been found together.\n", count);
			count = 0;
		}
		previousInput = input;
	}
}

void extraCredit_1(int k){
	int i = 1, answer = 2;
	while (i < k){
		answer *= 2;
		i++;
	}
	
	printf("The %i power of 2 is: %i\n", k, answer);
}

void extraCredit_2AND(int inputOne, int inputTwo, int inputThree){
	if (!inputOne && !inputTwo && !inputThree){
		printf("All three inputs are zero.\n");
	}
	else {
		printf("At least one of the inputs is non-zero.\n");
	}
}

void extraCredit_2OR(int inputOne, int inputTwo, int inputThree){
	if (inputOne || inputTwo || inputThree){
		printf("At least one of the inputs is non-zero.\n");
	}
	else {
		printf("All three inputs are zero.\n");
	}
}