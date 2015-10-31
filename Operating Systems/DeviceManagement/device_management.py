"""
    This program simulates the FCFS, SSTF, LOOK, and CLOOK device management algorithms

    Written by: Gunnar Holwerda
"""

from generation_and_calculation import *
from pprint import pprint


def calculate_seek_time(cur_position, new_position):
    """
        Calculates the seek time between the current_position and the new_position

        :param cur_position: dict, the current position of the head
        :param new_position: dict, where the head is going to move to
    """
    seek_time = 10 + 0.1 * (abs(new_position['track'] - cur_position['track']))

    # Check if we need to wrap around
    if cur_position['sector'] <= new_position['sector']:
        sector_transfer_time = 1 * \
            (new_position['sector'] - cur_position['sector'])
    else:
        sector_transfer_time = 1 * \
            ((MAX_SECTOR - cur_position['sector']) + new_position['sector'])
    # print("Total seek time to track {0}, sector {1} is {2:.2f}".format(new_position[
#       'track'], new_position['sector'], seek_time + sector_transfer_time))

    return seek_time + sector_transfer_time


def get_closest_request(current_position, requests):
    """
        Returns the closest request to the current position

        :param cur_position: dict, dictionary outlining the current position
        :param requests: list, list of requests to find the closest in
    """
    closest_seek_time = 10000
    closest_request = {}
    for request in requests:
        seek_time = calculate_seek_time(current_position, request)

        if seek_time < closest_seek_time:
            closest_seek_time = seek_time
            closest_request = request

    # pprint(closest_request)
    # print("\n")
    return closest_request


def get_next_request_in_cur_direction(asc, current_position, requests):
    """
        Returns the next job when we are traveling in direction

        :param asc: bool, true if the head is moving in ascending direction
        :param current_position: dict, the current position of the head
        :param requests: list, list of requests currently waiting

    """
    current_sector = current_position['sector']
    current_track = current_position['track']

    if not asc:
        # Get all jobs with sectors lower than the current
        waiting_requests = [i for i in requests if i[
            'sector'] < current_sector or
            (i['sector'] == current_sector and i['track'] <= current_track)]
        #print("DESC")
        #pprint(waiting_requests)
        closest_request = get_closest_request(current_position, waiting_requests)
    else:
        # Get all jobs with sectors higher than the current
        waiting_requests = [i for i in requests if i[
            'sector'] > current_position['sector'] or
            (i['sector'] == current_sector and i['track'] >= current_track)]
        #print("ASC")
        #pprint(waiting_requests)
        closest_request = get_closest_request(current_position, waiting_requests)

    return closest_request if closest_request else None

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
        if cur_time <= arrival_time:
            cur_time = arrival_time

        cur_seek_time = calculate_seek_time(current_position, request)
        finish_time = cur_time + cur_seek_time
        cur_time = finish_time
        turnaround_time = finish_time - arrival_time
        time_data.append(turnaround_time)
        current_position = request

    return time_data


def shortest_seek_time_first(test_data):
    """
        Runs the Shortest Seek Time First algorithm on the test data

        :param data: list, list of dictionaries that represent the requests
    """
    current_position = {
        'track': 0,
        'sector': 0
    }
    time_data = []
    cur_time = 0

    while test_data:
        # print("Time: {0:.2f}".format(cur_time))

        # Get all jobs that have arrived before the current_time
        waiting_requests = [i for i in test_data if i['arrival_time'] <= cur_time]

        if waiting_requests:
            # Get the closest request to this one
            next_request = get_closest_request(current_position, waiting_requests)
            # pprint(next_request)
            test_data.remove(next_request)
            # Calcuatle the seek time to the closest request
            cur_seek_time = calculate_seek_time(current_position, next_request)

            cur_time = cur_time + cur_seek_time
            turnaround_time = cur_time - next_request['arrival_time']
            time_data.append(turnaround_time)
            current_position = next_request
        else:
            cur_time += 1

    return time_data


def look(test_data):
    """
        Runs the LOOK algorithm on the test data

        :param data: list, list of dictionaries that represent the requests
    """
    current_position = {
        'track': 0,
        'sector': 0
    }
    time_data = []
    cur_time = 0
    cur_direction = True

    while test_data:
        #print("TIME: {0:.2f}".format(cur_time))
        # Get all jobs that have arrived before the current_time
        waiting_requests = [i for i in test_data if i['arrival_time'] <= cur_time]

        if waiting_requests:
            next_request = get_next_request_in_cur_direction(
                cur_direction, current_position, waiting_requests)

            if not next_request:
                cur_direction = not cur_direction
                # We have nothing in the current direction, check the other direction
                next_request = get_next_request_in_cur_direction(
                    cur_direction, current_position, waiting_requests)

            pprint(next_request)
            test_data.remove(next_request)
            # Calcuatle the seek time to the closest request
            cur_seek_time = calculate_seek_time(current_position, next_request)

            cur_time = cur_time + cur_seek_time
            turnaround_time = cur_time - next_request['arrival_time']
            time_data.append(turnaround_time)
            current_position = next_request
        else:
            cur_time += 1

    return time_data


def clook(data):
    """
        Runs the C-LOOK algorithm on the test data

        :param data: list, list of dictionaries that represent the requests
    """
    # TODO: implement this method
    pass

TEST_REQUESTS = get_predetermined_test_data()
print("********** FIRST COME FIRST SERVER  ****************")
print_statistics(first_come_first_serve(TEST_REQUESTS))

print("********** SHORTEST SEEK TIME FIRST ***************")
print_statistics(shortest_seek_time_first(TEST_REQUESTS))

print("**********          LOOK            ***************")
TEST_REQUESTS = get_predetermined_test_data()
print_statistics(look(TEST_REQUESTS))
