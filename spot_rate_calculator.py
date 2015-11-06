from math import pow

# A list of starting spot rates
spot_rates = {
	1: 0.0400,
	2: 0.0415,
	3: 0.04675827320114845,
	4: 0.04723981667171695,
}

def calculate_spot_rate(spot_rate_to_calculate, price, rate, spot_rates):
	future_value = 100
	spot_rate = 0
	value_of_previous_rates = 0
	# Calculate the payment for the bond
	payment = (future_value * rate) / 2

	# This creates a loop to add up all the previoius values before the one to calculate the
	# spot rate for
	for period in range(1, spot_rate_to_calculate):
		# Take the payment divided by 1 + the current periods spot rate to the power of the period
		value_of_previous_rates += (payment) / pow((1 + spot_rates[period]), period)

	# Calculate the current price - the value of previous rates
	price_minus_value_of_prev_rates = price - value_of_previous_rates

	# (1 + S) ^ NPER = (FV + PMT) / (price_minus_value_of_prev_rates)
	one_plus_spot_rate_to_the_period = (future_value + payment) / price_minus_value_of_prev_rates

	# S = one_plus_spot_rate_to_the_period ^ (1/3) - 1
	spot_rate = one_plus_spot_rate_to_the_period**(1 / spot_rate_to_calculate) - 1

	# This adds the spot rate to the dictionary of spot rates so it can be used for the next
	# calculation
	spot_rates[spot_rate_to_calculate] = spot_rate

	# Return the spot_rate to be printed out to the screen
	return spot_rate

# Calculate the spot rate for each value
print(calculate_spot_rate(3, 99.45, 0.085, spot_rates))
print(calculate_spot_rate(4, 99.64, 0.09, spot_rates))
print(calculate_spot_rate(5, 103.49, 0.11, spot_rates))
print(calculate_spot_rate(6, 99.49, 0.095, spot_rates))
print(calculate_spot_rate(7, 100, 0.1, spot_rates))
print(calculate_spot_rate(8, 98.72, 0.1, spot_rates))
print(calculate_spot_rate(9, 103.16, 0.115, spot_rates))
print(calculate_spot_rate(10, 92.24, 0.0875, spot_rates))
print(calculate_spot_rate(11, 98.38, 0.105, spot_rates))
print(calculate_spot_rate(12, 99.14, 0.11, spot_rates))
print(calculate_spot_rate(13, 86.94, 0.085, spot_rates))
print(calculate_spot_rate(14, 84.24, 0.0825, spot_rates))
print(calculate_spot_rate(15, 96.09, 0.11, spot_rates))
print(calculate_spot_rate(16, 72.62, 0.065, spot_rates))
print(calculate_spot_rate(17, 82.97, 0.0875, spot_rates))
print(calculate_spot_rate(18, 104.30, 0.13, spot_rates))
print(calculate_spot_rate(19, 95.06, 0.115, spot_rates))
print(calculate_spot_rate(20, 100, 0.125, spot_rates))
