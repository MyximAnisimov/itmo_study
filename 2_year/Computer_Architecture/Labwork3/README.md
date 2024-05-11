Синтаксис языка
---
Синтаксис в расширенной БНФ
* `[...]` - вхождение 0 или 1 раз
* `{...}` - вхождение 0 или несколько раз
* `{...}-` - вхождение 1 или несколько раз
```
program :: = section_data "\n" section_text

section_data :: = "section .data" "\n" { global_line }

section_text :: = "section .text" "\n" { text_line }

global_line :: = label_name ":"  variable [ comment ]

variable :: = integer
            | string
            | float
            | buffer
            | label_name

label_name :: = <any of " a-z A-Z "> { <any of " a-z A-Z 0-9 _ "> }

text_line :: = instr [ comment ]

instr :: = op0 register
         | op1 register ", " label_name
         | op2 register ", " register
         | op3 label_name  

op0 :: = "inc"
       | "dec"          

op1 :: = "load"
       | "store"     

op2 :: = "add"
       | "sub"
       | "mul"
       | "div"
       | "mod"

op3 :: = "call"
       | "halt"                

integer :: = [ " - " ] { <any of "0-9"> }-

positive_integer :: = { <any of "0-9"> }-

string :: = "\"" { <any of "a-z A-Z" > }- "\""

buffer :: = "bf " positive_integer

comment :: = ";" { <any symbol except "\n"> }
```
## Операции:
### branching_op
* `for` - цикл-for
* `if` - условие
* `else if` - условие "иначе если"
* `while` - цикл-while
### compare_op
* `==` - операция сравнения значений
* `<=` - операция больше равно
* `>=` - операция меньше равно
* `<` - операция меньше
* `>` - операция больше
* `!=` - операция не равно
### concatenation
* `&&` - логическое "И"
* `||` - логическое "ИЛИ"
### unary_op
* `++` - инкрементация
* `--` - декрементация
### binary_op
* `+` - операция сложения
* `-` - операция вычитания
* `*` - операция умножения
* `/` - операция деления
* `%` - операция нахождения остатка

Организация памяти
---
* Архитектура фон-Неймана
* Размер машинного слова - 32 бита
* Абсолютная адресция
### Схема памяти
```
+---------------------------+
| 00: start address (n)     |
| 01: interruption vector 1 |
| 02: input port            |
| 03: output port           |
| 04:       ...             |
|     variables space       |
|           ...             |
| n:  program start         |
|           ...             |
+---------------------------+
```
* В ячейке 00 лежит адрес начала программы
* В ячейке 01 лежит вектор прерывания
* В ячейках 02 и 03 хранятся порты ввода-вывода
* В ячейках с 04 по n хранятся переменные, используемые в программе:
 - `Целочисельные`
 - `Строковые`

