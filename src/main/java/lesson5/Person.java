package lesson5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person implements Serializable {

    private final String name;
    private final String surname;
    private final int age;

    private static final long serialVersionUID = 1L;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    //список персон заданного возраста
    public static List<Person> getListOfGivenAge(List<Person> listOfPerson, int age) {
        return listOfPerson.stream().filter(ages -> ages.getAge() == age).collect(Collectors.toList());
    }

    //сортировка по фамилии, а потом по имени
    public static List<Person> getSortedList(List<Person> listOfPerson) {
        return listOfPerson.stream()
                .sorted(new PersonSurnameComparator()
                        .thenComparing(new PersonNameComparator()))
                .collect(Collectors.toList());
    }

    //формируем отсортированный списк, исключая дубли
    public static List<Person> getListWithoutRepeat(List<Person> listOfPerson) {
        return listOfPerson.stream().distinct().collect(Collectors.toList());
    }

    //вывод на экран информации о персонее
    public static void printList(List<Person> listOfPerson) {
        System.out.printf("%-10s %-10s %-3s\n", "Фамилия", "Имя", "Возраст");
        listOfPerson.forEach(person -> System.out.printf("%-10s %-10s %7d\n",
                person.getSurname(),
                person.getName(),
                person.getAge()));
    }

    //запись в файл
    public static void writeToFile(List<Person> listOfPerson, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            listOfPerson.forEach(person -> {
                try {
                    oos.writeObject(person);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение из файла
    public static List<Person> readFromFile(String fileName) {
        List<Person> listOfPerson = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    listOfPerson.add((Person) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOfPerson;
    }

    //коллекцию (List<String>) только из Фамилии и Имени для прочтенных из фалов объектов
    public static void printListStringOfPerson(List<Person> listOfPerson) {
        List<String> stringList = listOfPerson.stream()
                .map(person -> person.getSurname() + " " + person.getName()).collect(Collectors.toList());
        stringList.forEach(System.out::println);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
}
