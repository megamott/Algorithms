from random import *
from time import time


def random_array():
    return [randint(-50, 50) for _ in range(0, 10**4)]


def selection_sort(arr):
    for i in range(len(arr) - 1):
        max_index = 0
        for j in range(len(arr) - i):
            if arr[j] > arr[max_index]:
                max_index = j
        arr[max_index], arr[len(arr) - i - 1] = \
            arr[len(arr) - i - 1], arr[max_index]

    return arr


if __name__ == '__main__':
    array = random_array()

    start = time()
    sorted_array = selection_sort(array)
    print(time() - start)
    print(sorted_array)
