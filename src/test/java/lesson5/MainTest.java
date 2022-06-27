package lesson5;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class MainTest extends Assert {

    private static final Person person1 = new Person("Анна", "Иванова", 15);
    private static final Person person2 = new Person("Иван", "Иванов", 25);
    private static final Person person3 = new Person("Юрий", "Петов", 21);
    private static final Person person4 = new Person("Анна", "Иванова", 15);
    private static final Person person5 = new Person("Максим", "Акимов", 25);
    private static final Person person6 = new Person("Максим", "Акимов", 21);
    private static final List<Person> listOfPerson1 = List.of(person1, person2, person3, person4, person5);
    private static final List<Person> listOfPerson2 = List.of(person1, person2, person3, person4, person5,
            person6);

    @Test
    public void getInformationAboutSortedList() {
        List<Person> listOfPersonSortedExtended1 = List.of(person5, person2, person1, person4, person3);
        assertEquals(listOfPersonSortedExtended1, Person.getSortedList(listOfPerson1));

        List<Person> listOfPersonSortedExtended2 = List.of(person5, person6, person2, person1, person4, person3);
        assertEquals(listOfPersonSortedExtended2, Person.getSortedList(listOfPerson2));
    }

    @Test
    public void getInformationAboutRepeatPerson() {
        List<Person> listOfPersonWithoutDoubleExtended1 = List.of(person1, person2, person3, person5);
        assertEquals(listOfPersonWithoutDoubleExtended1, Person.getListWithoutRepeat(listOfPerson1));

        List<Person> listOfPersonWithoutDoubleExtended2 = List.of(person1, person2, person3, person5, person6);
        assertEquals(listOfPersonWithoutDoubleExtended2, Person.getListWithoutRepeat(listOfPerson2));
    }

    @Test
    public void getInformationAboutAge() {
        List<Person> listOfPersonAgeEx1 = List.of(person3);
        assertEquals(listOfPersonAgeEx1, Person.getListOfGivenAge(listOfPerson1, 21));

        List<Person> listOfPersonAgeEx2 = List.of(person3, person6);
        assertEquals(listOfPersonAgeEx2, Person.getListOfGivenAge(listOfPerson2, 21));

        List<Person> listOfPersonAgeEx3 = List.of(person1, person4);
        assertEquals(listOfPersonAgeEx3, Person.getListOfGivenAge(listOfPerson2, 15));
    }


}
