/*
Gunnar Holwerda
*/

#include <stdlib.h>
#include <stdio.h>

typedef struct list_node {
	char letter_char;
	struct list_node* next_node;
} node_t;

void add_node(node_t* new_node, node_t* root_node);
void print_list(node_t* root_node);

int main(int argc, char* argv[]){
	
	node_t* root_node = malloc(sizeof(struct list_node));
	root_node->letter_char = argv[1][0];
	
	int loop_count = 1;
	int arg_count = 1;
	
	for (arg_count = 1; arg_count < argc; arg_count++){
		while(argv[arg_count][loop_count] != '\0'){
			node_t* new_node = malloc(sizeof(struct list_node));
			new_node->letter_char = argv[arg_count][loop_count];
			
			add_node(new_node, root_node);
			
			loop_count++;
		}
		loop_count = 0;
	}
	
	print_list(root_node);
	
	printf("I am going to print out the fourth character of the arguments, watch and learn boys:\n");
	printf("%c \n", root_node->next_node->next_node->next_node->letter_char);
	
	return 0;
}

void add_node(node_t* new_node, node_t* root_node){
	if (root_node->next_node == NULL){
		root_node->next_node = new_node;
	}
	else {
		add_node(new_node, root_node->next_node);
	}
}

void print_list(node_t* root_node){
	if (root_node != NULL){
		printf("%c->", root_node->letter_char);
		print_list(root_node->next_node);
	}
	else {
		printf("NULL\n");
	}
}