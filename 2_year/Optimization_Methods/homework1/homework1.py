import numpy as np


def f(x):
    return np.log(1 + x ** 2) - np.sin(x)


def derivative_f(x):
    return (2 * x) / (x ** 2 + 1) - np.cos(x)


def second_derivative_f(x):
    return np.sin(x) - (2 * (x ** 2) - 2) / (x ** 4 + 2 * (x ** 2) + 1)


def half_division(a, b, epsilon):
    counter = 1

    while (np.abs(b - a) >= 2 * epsilon):

        if (counter > 25):
            print("Достигнута 25-ая итерация")
            break

        x1 = (a + b - epsilon) / 2
        x2 = (a + b + epsilon) / 2
        print(f"n: {counter} | a= {a} | b= {b} \n")

        counter = counter + 1
        if (np.abs(b - a) / 2 < epsilon):
            break
        if (f(x1) <= f(x2)):
            b = x2
        else:
            a = x1

    print(f"Ответ: экстремум функции равен {round(f((b + a) / 2), 10)} при {round((b + a) / 2, 10)} \n")


def golden_ratio(a, b, eps):
    tau = (np.sqrt(5) - 1) / 2

    x1 = a + ((3 - np.sqrt(5)) / 2) * (b - a)
    x2 = a + ((np.sqrt(5) - 1) / 2) * (b - a)

    f_x1 = f(x1)
    f_x2 = f(x2)

    eps_n = ((b - a) / 2)
    iterator = 1

    while (((b - a) / 2) > eps):
        if (iterator > 25):
            print("25-ая итерация достигнута")
            break
        if (f_x1 < f_x2):
            b = x2
            x2 = x1
            f_x2 = f_x1
            x1 = b - tau * (b - a)
            f_x1 = f(x1)
        else:
            a = x1
            x1 = x2
            f_x1 = f_x2
            x2 = a + tau * (b - a)
            f_x2 = f(x2)
        eps_n *= tau
        print(f"n: {iterator} | a = {round(a // 0.001 / 1000, 3)} | b = {round(b // 0.001 / 1000, 3)} \n")
        iterator += 1

    print(f"Ответ найден | Экстремум функции равен {round(f((a + b) / 2))} при x= {round((a + b) / 2, 3)} \n")


def newton_method(x, eps):
    counter = 1
    while True:
        if counter > 25:
            print("Достигнута 25-ая итерация \n")
            break
        if abs(derivative_f(x)) < eps:
            print(f"Ответ: Экстремум функции {round(f(x), 10)} при x = {round(x, 10)} \n")
            break
        x_next = x - derivative_f(x) / second_derivative_f(x)
        print(f"n: {counter} | x = {x:.2e} | f`(x) = {derivative_f(x):.2e}")
        x = x_next
        counter = counter + 1


if __name__ == '__main__':
    print("МЕТОД ПОЛОВИННОГО ОТРЕЗКА  \n")
    half_division(0, np.pi / 4, 10 ** (-10))
    print("МЕТОД ЗОЛОТОГО СЕЧЕНИЯ \n")
    golden_ratio(0, np.pi / 4, 10 ** (-10))
    print("МЕТОД НЬЮТОНА \n")
    newton_method(0, 10 ** -10)