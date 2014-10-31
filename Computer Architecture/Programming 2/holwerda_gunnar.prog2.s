#############################################
## Name:  Gunnar Holwerda                   #
## Email: gunnarholwerda@gmail.com          #
#############################################
##                                          #
##  This program prints a bitmap of a       #
##  character based on vector of integers.  #
##                                          #
##  Dimensions: 9 bits x 10 rows            #
##  Mapping:    0 ==> '-'; 1 ==> 'X'        #
##                                          #
##  Variable List:                          #
##    # list variables used in main here    #
##                                          #
##    # but list all procedure variables    #
##    #             with procedure comments #
##                                          #
##                                          #
############################################# 

.globl main

#############################################
#                                           #
#                   Data                    #
#                                           #
#############################################
.data

seq1:	.word 60 66 157 293 329 329 334 304 131 124
newln: .asciiz "\n"
dollarsign: .asciiz "$"
hyphen: .asciiz "-"


#############################################
#                                           #
#                  Program                  #
#                                           #
#############################################
.text
main:
	dollar_sign:
		la $a0, seq1
		jal printchar
		jal exit
		# Your experiments go here

	
############################################# 
# Procedure: rowprint					    #
#   - prints out the given integer as a     #
#     single row of bits (0='-' and 1='X"). #
#										    #
#   - inputs : $a0 = int value to display   #
#   - outputs: none                         #  
#										    #
#############################################

rowprint:
	addi $sp, $sp, -4	# Move the stack pointer
	sw $ra 0($sp)		# Store ra because we will be calling functions

	# Thinking here since we know they are 10 bit numbers
	# We "and" 2^9 with the current number then divide 2^9 by 2 to get 2^8
	# Loop through again and compare again
	

#############################################	
## end procedure rowprint                  ##
#############################################	


	# Your other procedures go here...
#############################################
# Procedure: charprint                      #
#	- print the whole bitmapped char        #
#                                           #
#	-inputs : $a0 address of word array     #
#	-outputs: none                          #
#############################################
charprint: 
	addi $sp, $sp, -4		# Move stack pointer
	sw	$ra, 0($sp)			# Store $ra
	move $t0, $a0			# Move array into $t0
	li $t1, 0				# Set $t1 to 0
	li $t2, 10				# Place length of array in $t2 (10)
	loop:
		slt $t3, $t1, $t2,  # Checks if $t1 < $t2 and puts it in $t3
		bne $t3, 1, end_loop# Branch to end_loop if $t1 >= $t2
		lw $a0, 0($t0)		# Load first item from array into $a0
		jal rowprint		# Call rowprint
		jal newline			# Call newline
		addi $t1, $t1, 1 	# Increment i
		j loop				# Jump to top of loop
	end_loop:
		lw $ra, 0($sp)		# Restore $ra
		addi $sp, $sp, 4	# Move stack pointer back
		jr $ra				# Return

#############################################
## end procedure charprint                 ##
#############################################

#############################################
# Procedure: newline                        #
#	- print a new line                      #
#                                           #
#	-inputs : none                          #
#	-outputs: none                          #
#############################################
newline:
	addi $v0, $zero, 4	# Prepare print_string
	la	 $a0, newln		# Load newln into $a0
	syscall

#############################################
## end procedure newline                   ##
#############################################

#############################################
# Procedure: printchar                      #
#	- prints a char                         #
#                                           #
#	-inputs : $a0 - char to print           #
#	-outputs: none                          #
#############################################
printchar:
	li $v0, 11  # Load print_char
	syscall     # $char already in $a0 so print


#############################################
## end procedure newline                   ##
#############################################

############################################# 
# Procedure: exit   					    #
#   - exit the program                      #
#										    #
#   - inputs : none                         #
#   - outputs: none                         #  
#										    #
#############################################
exit:
	li $v0, 10				# Exit program
	syscall
	
#############################################	
## end program                             ##
#############################################	