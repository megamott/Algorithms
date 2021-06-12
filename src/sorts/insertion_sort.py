from random import *
from time import time


def random_array():
    return [randint(-50, 50) for _ in range(0, 10**3)]


def insertion_sort(arr):
    for i in range(1, len(arr)):
        j = i
        while j > 0 and arr[j] < arr[j - 1]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1
    return arr


if __name__ == '__main__':
    array = random_array()

    start = time()
    sorted_array = insertion_sort(array)
    print(time() - start)
    print(sorted_array)
