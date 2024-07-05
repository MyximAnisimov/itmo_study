package common.utility;

import common.collections.Person;
import common.exceptions.IncorrectDataInFileException;

import java.util.ArrayList;
import java.util.Deque;
import java.util.NoSuchElementException;

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
                ArrayList<Person> array = new ArrayList<>();
            for( Person person : array){
                if(person.getID() <= 0){
                   array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getName() == null || person.getName().equals("")){
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getCoordinates() == null) {
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getCreationDate() == null) {
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getHeight() < 0) {
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getPassportID() ==null) {
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
if(person.getCoordinates()!=null) {
    if(person.getCoordinates().getY()<-438){
        array.remove(person);
            throw new IncorrectDataInFileException();
    }
}
if(person.getLocation() != null){
                    if(person.getLocation().getX()==null) {
                        array.remove(person);
                        throw new IncorrectDataInFileException();
                    }

                if(person.getLocation().getZ() == null){
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }
                if(person.getLocation().getName().equals("")) {
                    array.remove(person);
                    throw new IncorrectDataInFileException();
                }}
else throw new NoSuchElementException();

            }}
            catch(IncorrectDataInFileException e){
                CustomConsole.printError("Проверьте файл на корректность введённых данных!");
            }

            return person;
        }
}
