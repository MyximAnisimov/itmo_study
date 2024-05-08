**
line :: = variable_name [ comment ] "\n"
  | [ comment ] "\n"
  | variable_name unary_op 
  | variable_name "=" variable_value binary_op variable_value
  | variable_name "=" complex_eq
  | operator1 "(" complex_log_eq " ")" “{“
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

operator1 :: = "for"
  | "if"
  | “else if”
            | "while"

equal_op :: = "=="
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

Операции:
++ - инкрементация
-- - декрементация
+ - операция сложения
- - операция вычитания
* - операция умножения
/ - операция деления
% - операция нахождения остатка

