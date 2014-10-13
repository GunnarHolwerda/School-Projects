.globl main

.data
num_prompt1: 	.asciiz "Enter the first number: "
num_prompt2: 	.asciiz "Enter the second number: "
sum_str_pt1: 	.asciiz "The result of "
sum_str_pt2:	.asciiz " plus "
sum_str_pt3:	.asciiz " is "
sum_str_end:	.asciiz "."

.text
main:
	li $v0, 4				# Load print_str
	la $a0, num_prompt1		# Place first prompt in 
	syscall					# Print to console

	li $v0, 5				# Load read_int
	syscall					# Read in int, answer in $v0

	add $t0, $v0, $zero		# Put the int into $t0

	li $v0, 4				# Load print_str
	la $a0, num_prompt2		# Place second prompt in 
	syscall					# Print to console

	li $v0, 5				# Load read_int
	syscall					# Read in int, answer in $v0

	add $t1, $v0, $zero		# Put the int into $t1
	add $t2, $t0, $t1		# Add the two numbers and place in $t2

	li $v0, 4				# Load print_str
	la $a0, sum_str_pt1		# Place "The result of " in 
	syscall					# Print to console

	li $v0, 1				# Load print_int
	add $a0, $t0, $zero		# Place $t0 in the arg
	syscall					# Print the number

	li $v0, 4				# Load print_str
	la $a0, sum_str_pt2		# Place "plus " in 
	syscall					# Print to console

	li $v0, 1				# Load print_int
	add $a0, $t1, $zero		# Place $t1 in the arg
	syscall					# Print the number

	li $v0, 4				# Load print_str
	la $a0, sum_str_pt3		# Place "is " in 
	syscall					# Print to console

	li $v0, 1				# Load print_int
	add $a0, $t2, $zero		# Place $t2 in the arg
	syscall					# Print the number

	li $v0, 4				# Load print_str
	la $a0, sum_str_end		# Place "." in 
	syscall					# Print to console

	li $v0, 10				# Load exit
	syscall					# Exit program