"""
    This program simulates the FCFS, SSTF, LOOK, and CLOOK device management algorithms

    Written by: Gunnar Holwerda
"""

import random
import math
from pprint import pprint

MAX_SECTOR = 8
MAX_TRACK = 250


def generate_test_data(num_of_requests):
    """
        Generates a random set of test requests for the program

        :param num_of_requests: int, number of requests to create
    """
    arrival_times = []
    cur_request = 0
    # Generate list of random arrivale times
    while cur_request < num_of_requests:
        arrival_times.append(random.randint(0, 99))
        cur_request += 1

    arrival_times.sort()
    test_data = []
    cur_request = 0
    while cur_request < num_of_requests:
        test_data.append({
            'arrival_time': arrival_times[cur_request],
            'track': random.randint(0, MAX_TRACK - 1),
            'sector': random.randint(0, MAX_SECTOR - 1)
        })
        cur_request += 1

    return test_data


def get_predetermined_test_data():
    """
        Returns the predetermined test data for the assignment
    """
    arrival_times = [0, 23, 26, 29, 35, 45, 57, 83, 88, 95]
    track_requested = [54, 132, 29, 23, 198, 170, 180, 78, 73, 249]
    sector_requested = [0, 6, 2, 1, 7, 5, 3, 4, 5, 7]

    test_data = []
    for i in range(len(arrival_times)):
        test_data.append({
            'arrival_time': arrival_times[i],
            'track': track_requested[i],
            'sector': sector_requested[i]
        })

    return test_data


def calculate_seek_time(cur_position, new_position):
    """
        Calculates the seek time between the current_position and the new_position

        :param cur_position: dict, the current position of the head
        :param new_position: dict, where the head is going to move to
    """
    # TODO: Figure out if the track needs to wrap around like 0 -> 249 -> 0 or if it can go back and
    # forth
    seek_time = 10 + 0.1 * (abs(new_position['track'] - cur_position['track']))

    # Check if we need to wrap around
    if cur_position['sector'] < new_position['sector']:
        sector_transfer_time = 1 * \
            (cur_position['sector'] - MAX_SECTOR) + new_position['sector']
    else:
        sector_transfer_time = 1 * \
            (cur_position['sector'] - new_position['sector'])

    return seek_time + sector_transfer_time


def calculate_statistics(data):
    """
        Calculates the average, variance, and stdev for a series of data

        :param data: list, a list of times to caluclate the stats for
    """
    total = 0
    for value in data:
        total += value
    average = total / len(data)

    total = 0
    for value in data:
        total += pow((value - average), 2)
    variance = total / len(data)

    stdev = math.sqrt(variance)

    return {
        'average': average,
        'variance': variance,
        'stdev': stdev
    }


def first_come_first_serve(test_data):
    """
        Runs the First Come First Serve disk management algorithm on the test_data

        :param test_data: list, a list of dictionaries of test data
    """
    current_position = {
        'track': 0,
        'sector': 0
    }
    time_data = []
    cur_time = 0
    for request in test_data:
        arrival_time = request['arrival_time']

        # If the cur_time is zero we are on the first request set the current
        # time to that
        if cur_time == 0:
            cur_time = arrival_time

        pprint(current_position)
        cur_seek_time = calculate_seek_time(current_position, request)
        print(cur_seek_time)
        finish_time = cur_time + cur_seek_time
        cur_time = finish_time
        time_data.append(finish_time - arrival_time)
        current_position = request

    return time_data


test_requests = get_predetermined_test_data()
results = first_come_first_serve(test_requests)
pprint(calculate_statistics(results))
