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
seq2:	.word 0 56 72 80 102 420 296 274 236 0
seq3: 	.word 16 40 68 130 254 130 130 130 130 130
seq4:	.word 480 272 264 264 304 264 264 264 272 480
seqX: 	.word 65 34 20 8 20 34 65 0 0 0
seqY:	.word 8 8 8 127 8 8 8 0 0 0
seqA:	.word 0 60 64 128 128 128 64 60 0
seqB: 	.word 0 248 132 130 130 130 130 132 248 0
newln: .asciiz "\n"
x_char: .asciiz "X "
hyphen: .asciiz "- "


#############################################
#                                           #
#                  Program                  #
#                                           #
#############################################
.text
main:
	A1:
		la $a0, seq1
		jal charprint
		jal newline
	A2:
		la $a0, seq2
		jal charprint
		jal newline
	A3:
		la $a0, seq3
		jal charprint
		jal newline
	A4:
		la $a0, seq4
		jal charprint
		jal newline
	B1:
		la $a0, seqX
		jal charprint
		jal newline
	B2:
		la $a0, seqY
		jal charprint
		jal newline
	C1:
		la $a0, seqA
		jal charprint
		jal newline
	C2:
		la $a0, seqB
		jal charprint
		jal newline
	
	jal exit

	
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
	addi $sp, $sp, -8				# Move the stack pointer
	sw $ra, 0($sp)					# Store ra because we will be calling functions
	sw $s0, 4($sp)
	move $s0, $a0					# Store $a0 into $s0
	li $s1, 256     				# Load 2^8 into $s1
	print_loop:
		beq $s1, $0, end_print_loop # If comparing bit string is 0 we need to leave
		and $t0, $s0, $s1  			# Logical and argument and 2^x
		bne $t0, $s1, bit_not_exist	# If result matches 2^x then we need to place an X
		la $a0, x_char				# Load the x into $a0
		jal printstring
		srl $s1, $s1, 1             # Shift the comparing bit string to the right
		j print_loop                # Jump back to loop
	bit_not_exist:
		la $a0, hyphen				# That specific bit isn't in the number so we print hyphen
		jal printstring
		srl $s1, $s1, 1             # Shift bit string to the right in order to check next bit
		j print_loop                # jump back to loop
	end_print_loop:
		lw $s0, 4($sp)
		lw $ra, 0($sp)				# Get $ra back from stack
		addi $sp, $sp, 8			# Move stack pointer back
		jr $ra						# Exit procedure

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
	addi $sp, $sp, -8		  # Move stack pointer
	sw	$ra, 0($sp)			  # Store $ra
	sw 	$s0, 4($sp)
	move $s0, $a0			  # Move array into $t0
	li $t1, 0				  # Set $t1 to 0
	li $t2, 10				  # Place length of array in $t2 (10)
	loop:
		slt $t3, $t1, $t2,    # Checks if $t1 is less than $t2 and puts it in $t3
		beq $t3, $0, end_loop # Branch to end_loop if $t1 >= $t2
		lw $a0, 0($s0)		  # Load item from array into $a0
		jal rowprint		  # Call rowprint
		jal newline			  # Call newline
		addi $t1, $t1, 1 	  # Increment i
		addi $s0, $s0, 4      # Move address of array to point to next element
		j loop				  # Jump to top of loop
	end_loop:
		lw $s0, 4($sp)
		lw $ra, 0($sp)		  # Restore $ra
		addi $sp, $sp, 8	  # Move stack pointer back
		jr $ra				  # Return

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
	jr $ra

#############################################
## end procedure newline                   ##
#############################################

#############################################
# Procedure: printstring                    #
#	- prints a string                       #
#                                           #
#	-inputs : $a0 - string to print         #
#	-outputs: none                          #
#############################################
printstring:
	li $v0, 4  # Load print_string
	syscall     # $char already in $a0 so print
	jr $ra


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