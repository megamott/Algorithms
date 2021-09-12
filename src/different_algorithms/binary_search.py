from random import randint


def binary_search(array, value):
    lower_bound = 0
    upper_bound = len(array) - 1

    while lower_bound <= upper_bound:
        center = (upper_bound + lower_bound) // 2

        if value == array[center]:
            return center
        elif value > array[center]:
            lower_bound = center + 1
        elif value < array[center]:
            upper_bound = center - 1

    return -1


for i in range(10):
    my_array = [randint(0, 10) for _ in range(10)]
    print(sorted(my_array))
    print(binary_search(sorted(my_array), 5))
