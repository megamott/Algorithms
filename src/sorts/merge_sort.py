from random import *
from time import time


def random_array():
    return [randint(-50, 50) for _ in range(0, 10**4)]


def merge(a, b):
    i = 0
    j = 0
    result = []
    while i < len(a) and j < len(b):
        if a[i] < b[j]:
            result.append(a[i])
            i += 1
        elif a[i] > b[j]:
            result.append(b[j])
            j += 1
        else:
            result.append(b[j])
            result.append(a[i])
            j += 1
            i += 1

    result += a[i:] + b[j:]
    return result


def merge_sort(arr):
    if len(arr) == 1:
        return arr

    return merge(merge_sort(arr[len(arr) // 2:]), merge_sort(arr[:len(arr) // 2]))


if __name__ == '__main__':
    array = random_array()

    start = time()
    sorted_array = merge_sort(array)
    print(time() - start)
    print(sorted_array)

