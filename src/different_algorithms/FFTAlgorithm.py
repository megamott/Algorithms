import numpy as np


# Discrete Fourier transform
def DFT_slow(x):
    x = np.asarray(x, dtype=float)  # array of data
    n = x.shape[0]
    m = np.arange(n)
    k = m.reshape((n, 1))  # string to column for the further multiplication with matrix
    M = np.exp(-2j * np.pi * k * m / n)  # matrix of e^((-2j*Pi/n)*k*m)
    return np.dot(M, x)  # [matrix]*[data]


# Fast Fourier transform
def FFT(x):
    x = np.asarray(x, dtype=float)
    n = x.shape[0]

    if n % 2 > 0:
        raise ValueError("size of x must be a power of 2")  # this function can't add zeros to matrix
    elif n <= 32:  # this cutoff should be optimized
        return DFT_slow(x)
    else:
        x_even = FFT(x[::2])  # every time get even elements (iter)
        x_odd = FFT(x[1::2])  # every time get odd elements (iter)
        factor = np.exp(-2j * np.pi * np.arange(n) / n)  # w^(j)
        return np.concatenate([x_even + factor[:int(n / 2)] * x_odd,  # x_e + w(j)*x_o for j elements
                               x_even + factor[int(n / 2):] * x_odd])  # for j + n/2 elements


# x = np.random.random(1024)
#
# print(np.allclose(FFT(x), np.fft.fft(x)))
