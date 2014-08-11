/*
Gunnar Holwerda
Lab 6
2/18/2014
*/

#include<stdio.h>
#include<stdlib.h>

void calculate_gravity_prob1(float kg_mass_one, float kg_mass_two, float distance_between_masses);
void problem_2_input_output(int num_as_input);
void problem_2_shuffle_left(char *letter_1_pntr, char *letter_2_pntr, char *letter_3_ptnr);
void extra_credit_1(int **ptr_coming_in);
void extra_credit_2(int *num_to_change);

int main() {
	calculate_gravity_prob1(1412341234, 23423423455, 1);
	problem_2_input_output(4);
	int dummy_val_1 = 1;
	int *dummy_ptr_1 = &dummy_val_1;
	extra_credit_1(&dummy_ptr_1);
	return 0;
}

//Calculates the gravitational force between two masses with a certain distance apart
void calculate_gravity_prob1(float kg_mass_one, float kg_mass_two, float distance_between_masses){
	float grav_const_G = .0000000000667357;

	kg_mass_one *= kg_mass_two;
	kg_mass_one *= grav_const_G;
	distance_between_masses *= distance_between_masses;
	kg_mass_one /= distance_between_masses;
	
	printf("force = %0.11f\n", kg_mass_one);
}

//Does what the problem 2 is supposed to in the instructions, grabs characters from the file and then loops through shuffling the letters over
void problem_2_input_output(int num_of_loops){
	FILE *in_file = fopen("i_goes_last.txt", "r");
	char file_first_letter, file_second_letter, file_third_letter;
	
	fscanf(in_file, "%c%c%c", &file_first_letter, &file_second_letter, &file_third_letter);
	//printf("First letter = %c\nSecond letter = %c\nThird letter = %c\n",file_first_letter, file_second_letter, file_third_letter); 
	
	int cntr_of_loops = 0;
	for(cntr_of_loops; cntr_of_loops < num_of_loops; cntr_of_loops++){
		problem_2_shuffle_left(&file_first_letter, &file_second_letter, &file_third_letter);
		printf("%c%c%c\n", file_first_letter, file_second_letter, file_third_letter);
	}
}

//Shuffles the letters over
void problem_2_shuffle_left(char *letter_1_pntr, char *letter_2_pntr, char *letter_3_pntr){
	char temp_char_1 = *letter_1_pntr;
	char temp_char_2 = *letter_2_pntr;
	char temp_char_3 = *letter_3_pntr;
	
	*letter_1_pntr = temp_char_3;
	*letter_2_pntr = temp_char_1;
	*letter_3_pntr = temp_char_2;
}

//Takes in a pointer to a pointer then changes the value of a local variable to something else
void extra_credit_1(int **ptr_coming_in){
	if(**ptr_coming_in == 1){
		int change_this_var = 5;
		extra_credit_2(&change_this_var);
		printf("Value was changed to : %i\n", change_this_var);
		return;
	}
	printf("Original Value : %i\n", **ptr_coming_in);
	**ptr_coming_in += 1;
	printf("New Value : %i\n", **ptr_coming_in);
}

//Calls extra_credit_1 with a pointer from something, in this case from itself
void extra_credit_2(int *num_to_change){
	extra_credit_1(&num_to_change);
}