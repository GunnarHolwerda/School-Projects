/*
Gunnar Holwerda
1/21/14
This is my work for lab 2
*/

#include<stdio.h>
#include<stdlib.h>

//Function type declarations
int calculateTotalBalance(int savings_balance, int checking_balance);
void operators(int input_one, int input_two);
void operatorsTUI();

//MAIN
int main() {
	puts("Gunnar Holwerda");
	
	int my_savings = 876,
		my_checking = 500;
	
	calculateTotalBalance(my_savings, my_checking);
	operatorsTUI();
	
	return 0;
}

/*
	Name: calculateTotalBalance
	Parameters:
		int savings_balance 	- balance in the savings account in cents
		int checking_balance 	- balance in the checking account in cents
		
	Function: Calculates the total balance between the savings and checking account
*/
int calculateTotalBalance(int savings_account_balance, int checking_account_balance) {
	/* PART TWO */
	printf("Your savings balance has: %i cents\nYour checking balance has: %i cents\n\n", savings_account_balance, checking_account_balance);	//Outputs balances for input
	
	int total_cents = savings_account_balance + checking_account_balance;			//Totals the two balances for a total balance
	int total_dollars = total_cents / 100;							//Finds the total number of dollars the user has
	int remaining_cents = total_cents % 100;						//Finds the remaining cents after converted to dollars
	
	printf("Your total balance is: %i dollars and %i cents\n", total_dollars, remaining_cents);		//Outputs total balance to console	
	
	
	/* PART ONE
	int savings_account_balance;
	int checking_account_balance;
	puts("Enter your savings account balance then checking account balance in cents: ");
	scanf("%i %i", &savings_account_balance, &checking_account_balance);
	
	printf("Total balance is %i (savings) + %i (checking) = %i cents.\n", savings_account_balance, checking_account_balance, total_cents);
	*/
	
	return total_cents;		//Returns the total balance, currently not being used for anything
}

/*
	Name: operators
	Parameters:
		int input_one 	- a number
		int input_two 	- a number
		
	Function: prints out the sum, difference, quotient, product and remainder of the two inputs.
*/
void operators(int input_one, int input_two){
	//Print out inputs
	printf("First input: %i\nSecond input: %i\n\n", input_one, input_two);
	
	//Print out addition of inputs
	printf("Sum: %i\n", input_one + input_two);
	
	//Print out subtraction of inputs
	printf("Difference: %i\n", input_one - input_two);
	
	//Print out multiplication of inputs
	printf("Product: %i\n", input_one * input_two);
	
	//Print out division of inputs
	printf("Quotient: %i\n", input_one / input_two);
	
	//Print out modular of inputs
	printf("Remainder: %i\n", input_one % input_two);
}

/*
	Name: operatorsTUI
	Parameters:
		
	Function: creates a TUI for the operators function
*/
void operatorsTUI(){
	int num_one = 0;
	int num_two = 0;
	puts("Please enter a whole number for me: ");
	scanf("%i", &num_one);
	puts("Great! Now enter one more whole number: ");
	scanf("%i", &num_two);
	
	operators(num_one, num_two);	
}