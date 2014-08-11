/*
Gunnar Holwerda
Lab 7
2/24/14
*/

#include<stdlib.h>
#include<stdio.h>

//Enumerated Type
typedef enum {pizza, soda, bread} food;

//Function prototype declarations
void get_cipher_array(char cipher_array_nums[]);
void decode_message_file(char cipher_array_nums[], int decode_this_num, FILE *output_file_name);
void print_food_name(food food_to_print);

int main(){
	char cipher_array_nums[26];
	int decode_this_num = 0;
	
	get_cipher_array(cipher_array_nums);
	
	FILE *message_file_name = fopen("message.txt", "r");
	FILE *decode_txt_file = fopen("decode.txt", "w");
	while(fscanf(message_file_name, "%i ,", &decode_this_num) == 1){
		decode_message_file(cipher_array_nums, decode_this_num, decode_txt_file);
	}
	fprintf(decode_txt_file, "\n");
	fclose(message_file_name);
	fclose(decode_txt_file);
	
	printf("Here is the extra credit: \n");
	print_food_name(pizza);
	
	return 0;
}

//Grabs all of the nums from the cipher.txt and saves them in an array for decoding
void get_cipher_array(char cipher_array_nums[]){
	FILE *cipher_file_name = fopen("cipher.txt", "r");
	
	int ASCII_start_char = 97;															//ASCII decimal for 'a'						
	int current_file_input = 0;
	while (fscanf(cipher_file_name, "%i, ", &current_file_input) == 1){
		cipher_array_nums[current_file_input] = ASCII_start_char;						//Assigns location in array to a character using ASCII values
		ASCII_start_char++;								
	}
	fclose(cipher_file_name);
}

//Goes through the message file number by number and returns the correct character
void decode_message_file(char cipher_array_nums[], int decode_this_num, FILE *output_file_name){
	fprintf(output_file_name, "%c", cipher_array_nums[decode_this_num]);
}

//Prints the food name of the food variable inputted
void print_food_name(food food_to_print){
	switch(food_to_print){
		case pizza:
			printf("Pizza\n");
			break;
		case soda:
			printf("Soda\n");
			break;
		case bread:
			printf("Bread");
			break;
		default:
			printf("Sorry I don't know what food that is...\n");
	}
}
