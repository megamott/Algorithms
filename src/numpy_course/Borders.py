# Name: Matvey Konoplyov
# Section:
# Team: BMSTU
# Assignment:
# Date: DAY MONTH YEAR

# Считайте 2 числа: n, m.
#
# Создайте матрицу размера n*m такую что:
# На границе матрицы будут стоять 1
# Внутри матрицы будут стоять 0
# Сохраните матрицу в переменную Z.
#
# ПРИМЕР
# Image: https://ucarecdn.com/25193712-fa68-44e8-9ad3-40747c17474c/

import numpy as np

Z = np.zeros(list(map(int, input().split(" "))))

Z[:, -1] = 1
Z[-1, :] = 1
Z[:, 0] = 1
Z[0, :] = 1

print(Z)
