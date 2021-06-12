from random import *
from time import time


def random_array():
    return [randint(-50, 50) for _ in range(10**4)]


def quick_sort_brute_force(arr):
    if len(arr) <= 1:
        return arr

    x = arr[randint(0, len(arr) - 1)]

    left = [el for el in arr if el < x]
    middle = [el for el in arr if el == x]
    right = [el for el in arr if el > x]

    return quick_sort_brute_force(left) + middle + quick_sort_brute_force(right)


def quick_sort(arr, start, end):  # maximum recursion depth, when len(arr)~10**5
    if start >= end:
        return

    p = partition(arr, start, end)
    quick_sort(arr, start, p - 1)
    quick_sort(arr, p + 1, end)


def partition(arr, start, end):
    pivot = arr[start]
    low = start + 1
    high = end

    while True:
        while low <= high and arr[low] <= pivot:
            low += 1
        while low <= high and arr[high] >= pivot:
            high -= 1
        if low <= high:
            arr[low], arr[high] = arr[high], arr[low]
        else:
            break

    arr[start], arr[high] = arr[high], arr[start]
    return high


if __name__ == '__main__':
    array = random_array()

    st = time()
    # quick_sort(array, 0, len(array) - 1)
    array = quick_sort_brute_force(array)
    print(time() - st)
    # print(array)

