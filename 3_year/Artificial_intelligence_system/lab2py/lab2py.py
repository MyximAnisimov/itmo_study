from pyswip import Prolog


def switch(arg):
    if arg == "чинить проводку" or arg == "Чинить проводку":
        response = input("Вы должны хорошо знать теллекоммуникационные сети, а так же техническое образование, чтобы чинить проводку.\n Вы имеете техническое образование? (Да/Нет): ")
        if response == "Да" or response == "да":
            response2 = input("Ваш GPA: ")
            if float(response2) < 3.0:
                response3 = input("К сожалению, ваших знаний недостаточно для того, чтобы чинить проводку. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные.\n Желаете взять что-то? (Да/Нет): ")
                if response3 == "Да" or response3 == "да":
                    response4 = input("Напишите желаемую роль: ")
                    if response4 == "скачивать данные" or arg == "Скачивать данные":
                        print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                        return False
                    elif response4 == "выкидывать мусор" or response4 == "Выкидывать мусор":
                        print("Отлично! Ваша задача очистить корабль от мусора")
                        return False
                    else:
                        print("Некорректный ввод")
                elif response3 == "Нет" or response3 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
            elif float(response2) >= 3.0 and float(response2) <= 4.0:
                print("Отлично! Ваша задача чинить проводку при поломке")
                return False
            else:
                print("Некорректный ввод. Повторите попытку")
        elif response == "Нет" or response == "нет":
            response5 = input("К сожалению, ваших знаний недостаточно для того, чтобы чинить проводку. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные. Желаете взять что-то? (Да/Нет)")
            while (True):
                if response5 == "Да" or response5 == "да":
                    response6 = input("Напишите желаемую роль: ")
                    while (True):
                        if response6 == "скачивать данные" or arg == "Скачивать данные":
                            print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                            return False
                        elif arg == "выкидывать мусор" or arg == "Выкидывать мусор":
                            print("Отлично! Ваша задача очистить корабль от мусора")
                            return False
                        else:
                            print("Некорректный ввод")
                elif response5 == "Нет" or response5 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
        else:
            print("Некорректный ввод. Повторите попытку")
    elif arg == "скачивать данные" or arg == "Скачивать данные":
        print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
        return False
    elif arg == "сканировать визоры" or arg == "Сканировать визоры":
        print("Отлично! Ваша задача сканировать других игроков в специальной комнате")
        response = input("Вы должны хорошо знать устройство рентген-аппарата, а так же иметь техническое образование в области физики, чтобы сканировать визоров.\n Вы имеете техническое образование в области физики? (Да/Нет): ")
        if response == "Да" or response == "да":
            response2 = input("Ваш GPA: ")
            if float(response2) < 3.0:
                response3 = input(
                    "К сожалению, ваших знаний недостаточно для того, чтобы сканировать визоры. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные, чинить проводку.\n Желаете взять что-то? (Да/Нет): ")
                if response3 == "Да" or response3 == "да":
                    response4 = input("Напишите желаемую роль: ")
                    if response4 == "скачивать данные" or arg == "Скачивать данные":
                        print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                        return False
                    elif response4 == "выкидывать мусор" or response4 == "Выкидывать мусор":
                        print("Отлично! Ваша задача очистить корабль от мусора")
                        return False
                    else:
                        print("Некорректный ввод")
                elif response3 == "Нет" or response3 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
            elif float(response2) >= 3.0 and float(response2) <= 4.0:
                print("Отлично! Ваша задача чинить проводку при поломке")
                return False
            else:
                print("Некорректный ввод. Повторите попытку")
        elif response == "Нет" or response == "нет":
            response5 = input(
                "К сожалению, ваших знаний недостаточно для того, чтобы сканировать визоры. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные. Желаете взять что-то? (Да/Нет)")
            while (True):
                if response5 == "Да" or response5 == "да":
                    response6 = input("Напишите желаемую роль: ")
                    while (True):
                        if response6 == "скачивать данные" or response6 == "Скачивать данные":
                            print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                            return False
                        elif response6 == "выкидывать мусор" or response6 == "Выкидывать мусор":
                            print("Отлично! Ваша задача очистить корабль от мусора")
                            return False
                        else:
                            print("Некорректный ввод")
                elif response5 == "Нет" or response5 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
        else:
            print("Некорректный ввод. Повторите попытку")
    elif arg == "выкидывать мусор" or arg == "Выкидывать мусор":
        print("Отлично! Ваша задача очистить корабль от мусора")
        return False
    elif arg == "чинить двигатели" or arg == "Чинить двигатели":
        response = input(
            "Вы должны иметь техническое образование в области аэрокосмической инженерии, чтобы чинить двигатели.\n Вы имеете техническое образование аэрокосмического инженера? (Да/Нет): ")
        if response == "Да" or response == "да":
            response2 = input("Ваш GPA: ")
            if float(response2) < 4.0:
                response3 = input("К сожалению, ваших знаний недостаточно для того, чтобы чинить двигатели. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные, чинить проводку.\n Желаете взять что-то? (Да/Нет): ")
                if response3 == "Да" or response3 == "да":
                    response4 = input("Напишите желаемую роль: ")
                    if response4 == "скачивать данные" or response4 == "Скачивать данные":
                        print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                        return False
                    elif response4 == "выкидывать мусор" or response4 == "Выкидывать мусор":
                        print("Отлично! Ваша задача очистить корабль от мусора")
                        return False
                    elif response4 == "чинить проводку" or response4 == "Чинить проводку":
                        print("Отлично! Ваша задача чинить проводку при поломке")
                        return False
                    else:
                        print("Некорректный ввод")
                elif response3 == "Нет" or response3 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
            elif float(response2) == 4.0:
                print("Отлично! Ваша задача чинить поломку двигателя корабля")
                return False
            else:
                print("Некорректный ввод. Повторите попытку")
        elif response == "Нет" or response == "нет":
            response5 = input(
                "К сожалению, ваших знаний недостаточно для того, чтобы чинить двигатели. \nРекомендуем вам выбрать одну из данных ролей: Выкидывать мусор, скачивать данные, чинить проводку. Желаете взять что-то? (Да/Нет)")
            while (True):
                if response5 == "Да" or response5 == "да":
                    response6 = input("Напишите желаемую роль: ")
                    while (True):
                        if response6 == "скачивать данные" or arg == "Скачивать данные":
                            print("Отлично! Ваша задача скачивать данные, когда появится необходимость")
                            return False
                        elif response6 == "выкидывать мусор" or response6 == "Выкидывать мусор":
                            print("Отлично! Ваша задача очистить корабль от мусора")
                            return False
                        elif response6 == "чинить проводку" or response6 == "Чинить проводку":
                            print("Отлично! Ваша задача чинить проводку при поломке")
                            return False
                        else:
                            print("Некорректный ввод")
                elif response5 == "Нет" or response5 == "нет":
                    print("К сожалению, вы не выбрали ни одной роли. Игра для вас недоступна")
                    return False
                else:
                    print("Некорректный ввод")
        else:
            print("Некорректный ввод. Повторите попытку")
    else:
        print("Некорректный ввод данных. Пожалуйста, повторите попытку")
        return False


def get_player_recommendations(player_name):
    prolog = Prolog()
    prolog.consult("lab1.pl")
    names = ["john", "jane", "bob", "mike", "chester", "max", "alice"]
    if player_name not in names:
        print("Некорректный ввод")
        return
    color = list(prolog.query(f"player({player_name}, Color)"))[0]['Color']
    role = list(prolog.query(f"role({player_name}, Role)"))[0]['Role']
    incorrect_data = True

    print(f"\nПривет {player_name}, ваш цвет: {color}\n\nВаша роль: {role}\n")

    tasks = [task['Task'] for task in prolog.query(f"can_complete_task({player_name}, Task)\n")]

    while (incorrect_data):
         if len(tasks) == 1:
             print("Вы предатель! Ваша задача незаметно убить весь экипаж")
             break
         elif tasks:
            print(f"Вы член экипиажа. Вам стоит выбрать одну из задач, которую вы будете выполнять: чинить проводку, скачивать данные, сканировать визоры, выкидывать мусор, чинить двигатели\n")
            player_task = input("Выберите одну из вышеперечисленных ролей: ")

            incorrect_data = switch(player_task)

            if not incorrect_data:
                break


if __name__ == "__main__":
    player_name = input("Привет! Выбери одно из предложенных имён: john, jane, bob, mike, chester, max, alice: ")
    get_player_recommendations(player_name)
    # print(line)
