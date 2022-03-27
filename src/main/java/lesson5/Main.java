package lesson5;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int NUMBER_OF_PERSON = 100;
    public static final int AGE = 21;
    public static final int MIN_RANGE_OF_AGE = 15;
    public static final int MAX_RANGE_OF_AGE = 30;
    public static final String FILE_NAME = "person.dat";

    public static void main(String[] args) {

        //генерируем группу из 100 человек в возрасте от 15 до 30
        List<Person> listOfPerson = new ArrayList<>(PersonGenerate.listOfPerson(NUMBER_OF_PERSON,
                MIN_RANGE_OF_AGE, MAX_RANGE_OF_AGE));

        //вывод персон заданного возраста
        Person.printList(Person.getListOfGivenAge(listOfPerson, AGE));
        System.out.println("_____________________________________");

        //cортировка списка по фамилии, а потом по имени
        List<Person> sortedListOfPerson = new ArrayList<>(Person.getSortedList(listOfPerson));

        //убираем дубли (если name, surname, age одинаковые)
        List<Person> listWithoutDouble = new ArrayList<>(Person.getListWithoutRepeat(sortedListOfPerson));

        //сохраняем в файл, как объект, каждый экземпляр класса Person
        Person.writeToFile(listWithoutDouble, FILE_NAME);

        //читаем из файла
        List<Person> listFromFile = Person.readFromFile(FILE_NAME);
//        Person.printList(listFromFile);
//        System.out.println("_____________________________________");

        //создаем при помощи stream новую коллекцию (List<String>) только из Фамилии и Имени
        // для прочтенных из фалов объектов и выводим на экран
        Person.printListStringOfPerson(listFromFile);
    }
}
