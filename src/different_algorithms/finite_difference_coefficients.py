import math
import numpy as np


def _build_rhs(offsets, deriv):
    """Построение правой матрицы для линейной системы уравнений"""
    b = [0 for _ in offsets]
    b[deriv] = math.factorial(deriv)

    return np.array(b, dtype='float')


def _build_matrix(offsets):
    """Построение матрицы линейной системы уравнений для определения конечно-разностных коэффициентов"""
    a = [([1 for _ in offsets])]
    for i in range(1, len(offsets)):
        a.append([j ** i for j in offsets])

    return np.array(a, dtype='float')


def _calc_coefs(deriv, offsets):
    """Решение системы линейных уравнений для определения конечно-разностных коэффициентов"""

    # Определение матриц - системы линейных уравнений
    matrix = _build_matrix(offsets)
    rhs = _build_rhs(offsets, deriv)

    # Решение системы линейных уравнений
    coefs = np.linalg.solve(matrix, rhs)

    return coefs


def coefficients(deriv, acc):
    """
    Функция определения конечно-разностных коэффициентов
    для произвольного порядка производной и количества плоскостей расчёта
    """

    # Определение сетки для коэффициентов
    num_central = 2 * math.floor((deriv + 1) / 2) - 1 + acc
    num_side = num_central // 2
    offsets = list(range(-num_side, num_side + 1))

    # Определение коэффициентов
    center = _calc_coefs(deriv, offsets)
    return center


print(coefficients(1, 4))
print(coefficients(1, 2) * (1, 2, 3))
print(type(coefficients(1, 2) * (1, 2, 3)))