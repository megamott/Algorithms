# Name: Matvey Konoplyov
# Section:
# Team: BMSTU
# Assignment:
# Date: DAY MONTH YEAR
# Считайте 2 числа: n, m.
# Создайте матрицу размера n*m и "раскрасьте" её в шахматную раскраску.
# 0 - "чёрное"
# 1 - "белое"
# Ячейка с координатами (0, 0) всегда "чёрная"
# (т.е. элемент (0, 0) равен 0).
# Матрицу сохраните в переменную Z.

import numpy as np

Z = np.ones(list(map(int, input().split())))

# Z[1::2, ::2] = 0
# Z[::2, 1::2] = 0

Z[::2, ::2] = 0
Z[1::2, 1::2] = 0

print(Z)
