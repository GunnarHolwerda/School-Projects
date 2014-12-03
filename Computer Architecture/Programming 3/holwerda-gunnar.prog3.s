#############################################
## Name:  Lab 3 Template File               #
## Email: address@email.com                 #
#############################################
##                                          #
##  This program produces a Lucas sequence  #
##  of the first (U) or second (V) order,   #
##  given a number N, and constants         #
##  P and Q.                                #
##                                          #
############################################# 

.globl main

#############################################
#                                           #
#                   Data                    #
#                                           #
#############################################
.data
	menuWelcomeMessage: .asciiz "Lucas Sequence Generator: \n\n"
	menuOption1message:  .asciiz "  (1) U(n, P, Q)\n\n"
	menuOption2message:  .asciiz "  (2) V(n, P, Q)\n\n"
	menuOption3message:  .asciiz "  (3) Exit the program\n\n"	
	selectionMessage:    .asciiz "Enter your selection: "
	requestNmessage:	 .asciiz "Please enter integer  N: "
	requestPmessage:	 .asciiz "Please enter constant P: "
	requestQmessage:	 .asciiz "Please enter constant Q: "	
	newline:             .asciiz "\n"
	notYetImplemented:	 .asciiz "\nThis procedure is not yet implemented!\n"
	exitMessage:         .asciiz "\nThank you, come again!"
	comma:				 .asciiz ", "
	errorMessage:		 .asciiz "That was not a valid input. Please try again.\n"
	
.text
#############################################
#                                           #
#                  Program                  #
#                                           #
#############################################
main:
	la $a0, menuWelcomeMessage	# load menu introductory message
	jal printString				# print message
	
	la $a0, menuOption1message	# load menu prompt 1
	jal printString				# print message
	
	la $a0, menuOption2message	# load menu prompt 2
	jal printString				# print message
	
	la $a0, menuOption3message	# load menu prompt 3
	jal printString				# print message
		
	la $a0, selectionMessage	# load message for menu selection input
	jal scanInteger			    # print selection prompt and get user input
	addi $a3, $v0, -1			# adjust result to make zero-indexed (0 or 1), 
	                            # and store value in $a3
	
	la $a0, newline          	# print a newline \n
	jal printString			
	
	li $t0, 1					# load temp value for range testing
	blt $t0, $a3, __sysExit		# user entered int > 2; exit program
	blt $a3, $0, __sysExit		# user entered int < 1; exit program
nMessage:	
	la $a0, requestNmessage   	# load message to enter integer N
	jal scanInteger			    # print selection prompt and get user input
	move $s0, $v0				# store n in $s0 (for now)
	slt $t0, $s0, $0
	beq $t0, $0, pMessage 
	la $a0, errorMessage
	jal printString
	j nMessage

pMessage:
	la $a0, requestPmessage   	# load message to enter integer P
	jal scanInteger			    # print selection prompt and get user input
	move $a1, $v0				# store P in $a1
	
	la $a0, requestQmessage   	# load message to enter integer Q
	jal scanInteger			    # print selection prompt and get user input
	move $a2, $v0				# store Q in $a2	
	
	move $a0, $s0				# copy n from $s0 to $a0
	
	jal lucasSequence			# print the lucas sequence for N, P, and Q

	la $a0, newline          	# print a newline \n
	jal printString		
	
	j main						# loop to main menu again


############################################# 
# Procedure: lucasSequence        		    #	
#############################################
#   - produces the Lucas sequence of the    #
#     first (U) or second (V) order for     #
#     given constants P and Q.              #
#                                           #
#     The procedure produces all numbers    #       
#     in the sequence U or V from n=0       #
#	  up to n=N.                     	    #
#                                           #
#   - inputs : $a0-integer N                #
#              $a1-constant P               #
#              $a2-constant Q               #
#              $a3-function U (0) or V (1)  #
#   - outputs: none                         #  
#										    #
#############################################	
lucasSequence:
	addi $sp, $sp, -4			# Move stack pointer
	sw $ra, 0($sp)				# Store return address on stack
	li $s0, 0					# Set $s0 to 0 to be the counter
	move $s1, $a0 				# Move N into $s1 
loop:
	move $a0, $s0				# Place the current number we are on into $a0
	jal lucasSequenceNumber		# Jump to calculate number
	move $a0, $v0 				# Place the result into $a0
	jal printInt				# Print the integer
	beq $s1, $s0, endLucasSeq  	# If we are on the last number skip printing comma
	la $a0, comma 				# Load the comma into $a0
	jal printString				# Print the comma
	addi $s0, $s0, 1			# Add 1 to the counter $s0
	j loop						# Jump back to the top of the loop
endLucasSeq: 
	lw $ra, 0($sp)				# Pop $ra off of stack
	addi $sp, $sp, 4 			# Move stack pointer back
	jr $ra 						# return
	
	
############################################# 
# Procedure: lucasSequenceNumber            #	
#############################################
#   - produces the Lucas number of the      #
#     first (U) and second (V) order for    #
#     number n, given constants P and Q.    #       
#										    #
#   - inputs : $a0-integer n                #
#              $a1-constant P               #
#              $a2-constant Q               #
#              $a3-function U (0) or V (1)  #
#   - outputs: $v0-value of U(n,P,Q) or     # 
#                  value of V(n,P,Q)        #
#										    #
#############################################	
lucasSequenceNumber:
baseCaseZERO:
	bne $a0, $0, baseCaseONE	# If current number is not 0 we go somewhere else
	bne $a3, $0, vSequenceZero  # Current number is 0, so we check if we are in the vSequence, if we are we jump, if not we continue and load 0
	li $v0, 0					# Place 0 into $v0
    jal $ra 				   	# Return
vSequenceZero:
	li $v0, 2					# Place 2 into $v0 because we are in a v sequence
	jal $ra 					# Return
baseCaseONE:
	li $t1, 1					# Load 1 into $t0 to compare with current number
	bne $a0, $t1, else 			# If the current number is != 1 then we jump to 'else'
	bne $a3, $0, vSequenceOne   # Current number is 0, so we check if we are in the vSequence, if we are we jump, if not we continue and load 0
	li $v0, 1					# Place 1 into $v0
    jal $ra 				   	# Return
vSequenceOne:
	move $v0, $a1				# Place P into $v0 because we are in a v sequence
	jal $ra 					# Return
else:
	addi $sp, $sp, -20			# Move stack pointer
	sw $ra, 16($sp)				# Store $ra on stack
	sw $a0, 12($sp)				# Store $a0 on stack
	sw $s0, 8($sp)				# Store previous $s0 on stack	
	sw $s1, 4($sp)				# Store previous $s1 on stack
	addi $a0, $a0, -1			# Set $a0 to n - 1
	jal lucasSequenceNumber     # Call lucasSequenceNumber
	move $s0, $v0 				# Place result in $s0
	sw $s0, 0($sp)				# Store $s0 on the stack
	lw $a0, 12($sp)				# Load $a0 from stack
	addi $a0, $a0, -2			# Set $a0 to n - 2
	jal lucasSequenceNumber     # Call lucasSequenceNumber
	move $s1, $v0 				# Store result in $s1
	lw $s0, 0($sp)				# Pop $s0 from stack
	mult $a1, $s0				# Calculate P * the result of n - 1
	mflo $t0	 				# Move result into $t0
	mult $a2, $s1				# Calculate Q * the result of n -2
	mflo $t1 	 				# Place result in $1
	sub $v0, $t0, $t1			# Subtract P*n-1 from Q * n-2
	lw $s1, 4($sp)				# Pop $s1 from stack
	lw $s0, 8($sp)				# Pop $s0 from stack
	lw $ra, 16($sp)				# Pop $ra from stack
	addi $sp, $sp, 20			# Move stack pointer back
	jr $ra 						# Return
	
	
############################################# 
# Procedure: scanInteger         		    #	
#############################################
#   - prints a message and gets an integer  #
#     from user                             #
#										    #
#   - inputs : $a0-address of string prompt #
#   - outputs: $v0-integer return value     #  
#										    #
#############################################	
scanInteger:
	addi $sp, $sp, -4			# adjust stack
	sw $ra, 0($sp)				# push return address
	jal printString             # print message prompt
	
	li $v0, 5					# read integer from console
	syscall						

	lw $ra, 0($sp)				# pop return address
	addi $sp, $sp, 4			# adjust stack
	jr $ra						# return
	
############################################# 
# Procedure: printString   				    #	
#############################################
#   - print a string to console             #
#										    #
#   - inputs : $a0 - address of string      #
#   - outputs: none                         #  
#										    #
#############################################
printString:
	li $v0, 4
	syscall
	jr $ra	

############################################# 
# Procedure: printInt      				    #	
#############################################
#   - print a int    to console             #
#										    #
#   - inputs : $a0 - int to print           #
#   - outputs: none                         #  
#										    #
#############################################
printInt:
	li $v0, 1
	syscall
	jr $ra	
	
############################################# 
# Procedure: __sysExit   				    #	
#############################################
#   - exit the program                      #
#										    #
#   - inputs : none                         #
#   - outputs: none                         #  
#										    #
#############################################
__sysExit:
	la $a0, exitMessage		# print exit message
	jal printString
	li $v0, 10				# exit program
	syscall

	