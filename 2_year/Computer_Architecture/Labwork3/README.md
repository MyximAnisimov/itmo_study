Синтаксис языка
---
Синтаксис в расширенной БНФ
* `[...]` - вхождение 0 или 1 раз
* `{...}` - вхождение 0 или несколько раз
* `{...}-` - вхождение 1 или несколько раз
```
line :: = variable_name [ comment ] "\n"
  | [ comment ] "\n"
  | variable_name unary_op 
  | variable_name "=" variable_value binary_op variable_value
  | variable_name "=" complex_eq
  | branching_op "(" complex_log_eq " ")" “{“
  | “}”

program :: = { line }

variable_name :: = <any of "a-z A-Z"> { <any of "a-z A-Z _ "0-9"> }

variable :: = "var" variable_name "=" variable_value

variable_value :: = integer 
  | positive_integer
  | string
  | char
  | variable_name

logical_eq :: = "(" variable_value equal_op variable_value ")" 

complex_log_eq :: = logical_eq { concatenation logical_eq }

simple_eq :: = variable_name { binary_op variable_name }

complex_eq :: = some_eq { binary_op some_eq }

comment :: = "//" <any symbols except "\n">

branching_op :: = "for"
  | "if"
  | “else if”
  | "while"

compare_op :: = "=="
  | "<="
  | ">="
  | "!="
  | "<"
  | ">"

concatenation :: = “&&” 
  | “||”

unary_op :: = "++"
  | "- -"
 
binary_op :: = "+"
  | "-"
  | "/"
  | "*"
  | "%"

integer :: = [ "-" ] { <any of "0-9"> }-

positive_integer :: = { <any of "0-9">}

string :: = "\"" { <any symbol except "\t \n"> } "\""

char :: = "\'" { <any symbol except "\t \n"> } "\'"
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
* В ячейке 00 лежит адрес начала программы
* В ячейке 01 лежит вектор прерывания
* В ячейках 02 и 03 хранятся порты ввода-вывода
* В ячейках с 04 по n хранятся переменные, используемые в программе:
** `Целочисельные`
** `Строковые`

