package tools;

import collections.Person;
import exceptions.IncorrectDataInFileException;
import exceptions.MustBeNotEmptyException;

import java.util.*;

import static collections.Country.names;

/**
 * Класс для проверки введённых данных коллекции на корректность
 */
public class Validator {
        Deque<Person> person;

        public Validator(Deque<Person> person) {
            this.person = person;
        }

        public Deque<Person> validate(){
            try{
            for(Iterator<Person> iterator = person.iterator();
                iterator.hasNext(); ){
                Person org = iterator.next();
                if(org.getID() <= 0){
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getName() == null || org.getName().equals("")){
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getCoordinates() == null) {
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getCreationDate() == null) {
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getHeight() < 0) {
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getPassportID() ==null) {
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
if(org.getCoordinates()!=null) {
    if(org.getCoordinates().getY()<-438){
            iterator.remove();
            throw new IncorrectDataInFileException();
    }
}
if(org.getLocation() != null){
                    if(org.getLocation().getX()==null) {
                        iterator.remove();
                        throw new IncorrectDataInFileException();
                    }

                if(org.getLocation().getZ() == null){
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }
                if(org.getLocation().getName().equals("")) {
                    iterator.remove();
                    throw new IncorrectDataInFileException();
                }}
else throw new NoSuchElementException();

            }}
            catch(IncorrectDataInFileException e){
                Console.printError("Проверьте файл на корректность введённых данных!");
            }

            return person;
        }
}
