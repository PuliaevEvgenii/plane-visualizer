Написать программу динамического поворота 3D-графика функции. 
Варианты функций:

z = e^sin(x*3) - cos (y^2)

z = x^3 - y^2

z = x^(1/3) * sin(y)

z = ln(x^2 + 1) / (y^2 + 2)

z = (1 / (x^3 + 2)) + ln (y)

z = abs(y^4 - y^3 + x^2 - x)

z = x^4+y^4*(x^2+y^2)

(*) z = e^((sin(x) + cos(x)) / (x^2 + 1))

(*) Усложнённая версия этого задания подразумевает добавление цветовой градации по оси z. Набор цветов и интервалы применения задаются пользователем отдельно.
Пользователь выбирает выводимый на экран график функции, выбирает трёхмерную область обзора, а также детализацию графика (количество опорных точек). После этого, пользователь задаёт угловую скорость и ось вращения (не обязательно одна из осей координат) и камера начинает своё вращение вокруг заданной оси. Для удобства стоит также как-то задавать точку, в которую при вращении смотрит камера.