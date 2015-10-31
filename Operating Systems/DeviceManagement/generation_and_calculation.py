"""
    This file houses all functions to generate the tset data and also all functions to
    calculate the stats on the time_series
"""

import random
import math

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


def print_statistics(time_series):
    """
        Prints out the statistics for the results

        :param results: list, time series of turnaround times for an algorithm
    """
    stats = calculate_statistics(time_series)
    print("Average: {0:.2f}".format(stats['average']))
    print("Standard Deviation: {0:.2f}".format(stats['stdev']))
    print("Variance: {0:.2f}".format(stats['variance']))
