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
         | op1 register ", " memory_address
         | op2 register ", " register ", " register
         | op3 label_name
         | op4         

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
       | "jmp"
       | "jz" 
       | "jnz"

op4 :: = "ei"
       | "di"
       | "ret"
       | "iret"
       | "halt"                          
                           

register :: = "r" <any of "0-9">

memory_address :: = <any of "0-9> <any of "0-9">

integer :: = [ " - " ] { <any of "0-9"> }-

positive_integer :: = { <any of "0-9"> }-

string :: = "\"" { <any of "a-z A-Z" > }- "\""

buffer :: = "bf " positive_integer

comment :: = ";" { <any symbol except "\n"> }
```
### Операции:
#### op0
* `inc { register }` - операция инкрементации значения в регистре
* `dec { register }` - операция декрментации значения в регистре
#### op1
* `load { register memory_address }` - загрузка данных из памяти в регистр
* `store { register memory_address }` - загрузка данных из регистра в память
#### op2
* `add { register register register }` - сложение второго регистра с третьим и сохранение результата в первом регистре
* `sub { register register register }` - вычитание третьего регистра из второго и сохранение результата в первом регистре
* `mul { register register register }` - умножение второго и третьего регистра и сохранение результата в первом регистре
* `div { register register register }` - деление второго регистра на третий и сохранение результата в первом регистре
* `mod { register register register }` - нахождение остатка деления второго регистра на третий и сохранение результата в первом регистре
#### op3
* `call { label_name }` - вызов пдопрограммы по указанной метке
* `jmp { label_name }` - безусловный переход на указанную метку
* `jz {label_name }` - переход на указанную метку если `z-flag` равен 1
* `jnz { label_name }` - переход на указанную метку если `z-flag` равен 0
#### op4
* `ei` - разрешить прерывание
* `di` - запретить прерывание
* `ret` - выход из подпрограммы
* `iret` - выход из текущего прерывания
* `halt` - остановка программы
  
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

