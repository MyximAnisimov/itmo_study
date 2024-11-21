package managers;

import collections.Coordinates;
import collections.Country;
import collections.Location;
import collections.Person;
import exceptions.*;
import tools.Console;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для создания элемента коллекции (Человека) через ввод пользователя
 */
public class PersonAdder {
    CollectionManager collMan;
    Scanner sc;
    private boolean scriptMode;

    /**
     * Функция для возврата объекта класса Scanner. Предназначена для считывания ввода пользователя
     * @return
     */
    public Scanner getUserScanner() {
        return sc;
    }

    /**
     * Задаёт значение для объекта класса Scanner
     * @param sc объект класса Scanner, который используется для считывания введённых пользователем данных
     */
    public void setUserScanner(Scanner sc) {
        this.sc = sc;
    }

    public PersonAdder(CollectionManager collMan){
        this.collMan=collMan;
        scriptMode=false;
    }

    /**
     * Устанавливает значение true для исполнения скрипта. Начало работы скрипта
     */
    public void setScriptMode(){
        scriptMode = true;
    }

    /**
     * Устанавливает значение false для исполнения скрипта. Конец работы скрипта
     */
    public void setUserMode(){
        scriptMode = false;
    }

    /**
     * Задаёт значение id для элемента коллекции
     * @return сгенерированное значение id
     */
    public int setID() {
        return collMan.generatorID();
    }

    /**
     * Задаёт имя для нового элемента коллекции
     * @param sc введённое пользователем имя элемента
     * @return имя нового элемента
     * @throws IncorrectInputInScriptException
     */
    public  String setName(Scanner sc) throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Введите имя человека: ");
            try {
                name = sc.nextLine().trim();
                if(scriptMode) Console.printLn(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("имя не должно быть пустым!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printError("имя не может быть загружено");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    Console.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     *  Задаёт координаты нового элемента коллекции
     * @param sc введённые пользователем координаты
     * @return элемент класса Coordinates с координатами
     * @throws IncorrectInputInScriptException
     * @throws OutOfLimitsException
     */
    public Coordinates setCoordinates(Scanner sc) throws IncorrectInputInScriptException, OutOfLimitsException {
        sc=new Scanner(System.in);
        double x;
        while (true) {
            try {
                Console.print("Введите координату х: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();
                x = Double.parseDouble(s);
                break;
            } catch (NumberFormatException e) {
                Console.printError("Координата X должна быть формата double");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("Координата X не может быть пуста!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        int y;
        while (true) {
            try {
                Console.print("Введите координату y: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();

                y =Integer.parseInt(s);
                if(y<-438) throw new OutOfLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("Координата Y не может быть загружена");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    Console.printError("Выполнен выход при помощи команды Ctrl+D!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                Console.printError("Координата Y должна быть формата int");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                Console.printError("Координата Y не может быть пуста!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
            catch(OutOfLimitsException e){
                Console.printError("Координата Y меньше -438!");
            }

        }
        Coordinates coord=new Coordinates(x,y);
        return coord;
    }

    /**
     * Выводит дату создания коллекции
     * @return дата создания коллекции
     */
    public LocalDate setCreationDate(){
        while (true) {
            try {
                return LocalDate.now();
            } catch (DateTimeException e) {
                Console.printError("Возникла ошибка с датой создания");
            }
        }
    }

    /**
     * Задаёт рост нового элемента коллекции
     * @param sc введённое пользователем значение роста
     * @return рост элемента коллекции (Человека)
     * @throws IncorrectInputInScriptException
     */
    public Integer setHeight(Scanner sc)throws IncorrectInputInScriptException{
        Integer height;
        while(true){
        try{
            Console.print("Введите рост человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) Console.printLn(s);
            if (s.equals("")) throw new MustBeNotEmptyException();

            height =Integer.parseInt(s);
            if(height<=0) throw new OutOfLimitsException();
            break;}
        catch(MustBeNotEmptyException e){
            Console.printError("имя не должно быть пустым!");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch (NumberFormatException e) {
            Console.printError("Рост человек должен быть в формате Integer");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch(OutOfLimitsException e){
            Console.printError("Поле height должно быть больше нуля!");
        }}
        return height;
    }

    /**
     * Задаёт значение даты дня рождения человека
     * @param sc введённые пользователем данные
     * @return элемент класса LocalDate с датой дня рождения
     * @throws IncorrectInputInScriptException
     * @throws WrongDateFormatException
     */
    public LocalDate setBirthdayDate(Scanner sc) throws IncorrectInputInScriptException,WrongDateFormatException {
        sc=new Scanner(System.in);
        LocalDate localdate;
        int year;
        while(true){
            try{
            Console.print("Введите год рождения человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) Console.printLn(s);
            if (s.equals("")){
                year=0;
            }
            else{
            year = Integer.parseInt(s);
            if(year<0||year>LocalDate.now().getYear()) throw new WrongDateFormatException();}
            break;}
            catch (NumberFormatException e) {
                Console.printError("Год рождения должен быть в формате int");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        catch(WrongDateFormatException e){
            Console.printError("Некорректный ввод года рождения!");
            if(scriptMode) throw new IncorrectInputInScriptException();}
        }
        int month;
        while(true){
            try{
                Console.print("Введите месяц рождения человека: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals("")){
                    month=0;
                }
                else{
                month = Integer.parseInt(s);
                if(month<0||month>12) throw new WrongDateFormatException();}
                break;}
            catch (NumberFormatException e) {
                Console.printError("Месяц рождения должен быть в формате int!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        catch(WrongDateFormatException e){
            Console.printError("Некорректный ввод месяца рождения!");
            if(scriptMode) throw new WrongDateFormatException();}
        }
        int day;
        while(true){
        try{
            Console.print("Введите день рождения человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) Console.printLn(s);
            if (s.equals("")){
                day=0;
            }else{
            day = Integer.parseInt(s);
                if(day<0||day>31) throw new WrongDateFormatException();
            if(month==2&&day>29) throw new WrongDateFormatException();}
            break;}
        catch (NumberFormatException e) {
            Console.printError("День рождения должен быть в формате int");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch(WrongDateFormatException e){
        Console.printError("Некорректный ввод дня рождения!");
        if(scriptMode) throw new IncorrectInputInScriptException();}
        }
        if(day==0&&year==0&&month==0){
           localdate=null;
        }
        else{
        localdate=LocalDate.of(year,month,day);
        }
        return localdate;
    }

    /**
     * Задаёт значение id паспорта
     * @param sc введённые пользоавтелем данные
     * @return id паспорта
     * @throws IncorrectInputInScriptException
     */
    public String setPassportID(Scanner sc)throws IncorrectInputInScriptException{
        sc=new Scanner(System.in);
        String passportID;
        while (true) {
            Console.print("Введите номер паспорта: ");
            try {
                passportID = sc.nextLine().trim();
                if(scriptMode) Console.printLn(passportID);
                if (passportID.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                Console.printError("ID паспорта не должно быть пустым!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                Console.printError("ID паспорта не может быть загружено");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    Console.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return passportID;
    }

    /**
     * Задаёт националньность человека
     * @param sc введённые пользователем данные
     * @return страна, в которой родился человек
     * @throws IncorrectInputInScriptException
     * @throws UnknownCountryException
     */
    public Country setNationality(Scanner sc) throws IncorrectInputInScriptException,UnknownCountryException{
        sc=new Scanner(System.in);
        Country nationality;
        while(true){
        try{

        Console.printLn("Укажите национальность человека, представленную в списке: ");
        Console.printLn(Country.INDIA);
        Console.printLn(Country.ITALY);
        Console.printLn(Country.JAPAN);
        Console.printLn(Country.VATICAN);
        Console.printLn(Country.UNITED_KINGDOM);
            String country=sc.nextLine();
        if(country.equals("JAPAN")){
            nationality = Country.JAPAN;
            return nationality;
        }
        else if(country.equals("INDIA")){
            nationality=Country.INDIA;
            return nationality;
        }
        else if(country.equals("ITALY")){
            nationality= Country.ITALY;
            return nationality;
        }
        else if(country.equals("VATICAN")){
            nationality= Country.VATICAN;
            return nationality;
        }
        else if(country.equals("UNITED KINGDOM")){
            nationality= Country.UNITED_KINGDOM;
            return nationality;
        }
        else throw new UnknownCountryException();}
        catch(UnknownCountryException e){
            Console.printError("Национальность человека не распознана. Повторите попытку");
            if(scriptMode) throw new IncorrectInputInScriptException();
        }}
    }

    /**
     * Задаёт значение координат и название места, в котором находится человек
     * @param sc введённые пользователем данные
     * @return элемент класса Location, в котором указаны координаты и название места
     * @throws IncorrectInputInScriptException
     */
    public Location setLocation(Scanner sc) throws IncorrectInputInScriptException{
        sc=new Scanner(System.in);
        Long x;
        while(true){
            try{
                Console.print("Введите координату Х локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals(""))throw new MustBeNotEmptyException();
                x = Long.parseLong(s);
                break;}
            catch (MustBeNotEmptyException e) {
                Console.printError("Координата Х не может быть пустой!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }catch (NoSuchElementException e) {
                Console.printError("Координата Х не может быть загружена!");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    Console.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        int y;
        while(true){
            try{
                Console.print("Введите координату Y локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals("")){
                    y=0;
                }
                else{
                    y = Integer.parseInt(s);}
                break;}
            catch (NoSuchElementException e) {
                Console.printError("Координата Y не может быть загружена!");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    Console.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        Float z;
        while(true){
            try{
                Console.print("Введите координату Z локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) Console.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();
                    z = Float.parseFloat(s);
                break;}
            catch (NumberFormatException e) {
                Console.printError("Координата Z должна быть в формате Float");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
            catch (MustBeNotEmptyException e) {
                Console.printError("Координата Z не может быть пустой!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }

        }
        String name;
        while(true){
        Console.print("Введите название локации: ");
        try {
            name = sc.nextLine().trim();
            if(scriptMode) Console.printLn(name);
            if (name.equals("")){
                name="null";
            }
            break;
        }
        catch (NoSuchElementException e) {
            Console.printError("название локации не может быть загружено");
            if (scriptMode) throw new IncorrectInputInScriptException();
            if(!sc.hasNext()) {
                Console.printError("Выполнен выход из программы!");
                System.exit(0);
            }
        } catch (IllegalStateException e) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
    }
         Location location = new Location(x,y,z,name);
        return location;
    }

    /**
     * Возвращает готовый элемент коллекции
     * @return элемент коллекции
     */
    public Deque<Person> getCollectionManager(){
        if(!collMan.getCollection().isEmpty()){
            Console.printLn(":(");
        }
        return collMan.getCollection();

    }
}
