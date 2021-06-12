from random import *
from time import time


def random_array():
    return [randint(-50, 50) for _ in range(0, 10**3)]


def bubble_sort(arr):
    for i in range(len(arr)):
        # k = 0
        for j in range(len(arr) - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                # k = 1
        # if k == 0:
        #     break
    return arr


if __name__ == '__main__':
    array = random_array()

    start = time()
    sorted_array = bubble_sort(array)
    print(time() - start)
    print(sorted_array)

