.globl main
.data
a: .word 10
.text
main:
	la $t0, a  			# Loads a into $t0 from RAM ($t0 = 10)
	lw $t1, 0($t0)		# Puts 0($t0) into $t1 which is the value of a ($t1 = 10)
	li $t2, 4			# Puts 4 into $t2 ($t2 = 4)
	sub $t0, $t1, $t2	# $t0 = $t1 - $t2, $t0 = 10 - 4, $t0 = 6
	addi $t3, $t0, 8	# $t3 = $t0 + 8, $t3 = 6 + 8, $t3 = 14
	add $a0, $t3, $zero	# $a0 = 14
	addi $v0, $zero, 1  # $v0 = 1, Integer print syscall
	syscall				# print integer in $a0 (print 14)
	li $v0, 10			# Load terminate syscall
	syscall				# Execute terminate syscall