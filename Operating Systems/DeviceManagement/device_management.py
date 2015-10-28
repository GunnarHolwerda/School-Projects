"""
    This program simulates the FCFS, SSTF, LOOK, and CLOOK device management algorithms

    Written by: Gunnar Holwerda
"""

import random

def generate_test_data(num_of_requests):
    """
        Generates a random set of test requests for the program
    """
    test_data = {}
    cur_request = 0
    while cur_request < num_of_requests:
        test_data[cur_request]["Arrival Time"] = random.randint(0, 99)
        test_data[cur_request]["Track"] = random.randint(0, 249)
        test_data[cur_request]["Sector"] = random.randint(0, 7)

    return test_data

def get_earliest_request(test_data):
    """
        Returns the request with the earliest arrival time and removes it from the dictionary
    """
    earliest_request = test_data[0]["Arrival Time"]
    data = None
    for data in test_data:
        if data["Arrival Time"] < earliest_request:
            earliest_request = data

    if data:
        test_data.pop(data)

    return data

value = generate_test_data(50)
print(get_earliest_request(value))
