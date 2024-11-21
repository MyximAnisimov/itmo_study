package tools;

import collections.Person;
import exceptions.IncorrectDataInFileException;

import java.util.*;

/**
 * Класс для проверки введённых данных коллекции на корректность
 */
public class Validator {
        Deque<Person> person;

        public Validator(Deque<Person> person) {
            this.person = person;
        }


}
