package lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerate {
    private static final Random random = new Random();

    //генерируем имя
    private static String personName() {
        List<String> listOfName = List.of("Анна", "Иван", "Мария", "Александр", "Петр", "Ольга", "Сергей",
                "Елена", "Наталья", "Павел", "Дмитрий", "Валерий", "Андрей", "Василиса", "Надежда");
        return listOfName.get(random.nextInt(listOfName.size()));
    }

    //генерируем фамилию
    private static String personSurname() {
        List<String> listOfSurname = List.of("Токарь", "Суббот", "Молоток", "Гвоздь", "Имбирь", "Мороз", "Слесарь",
                "Гриб","Голуб", "Гамза", "Довнар");
        return listOfSurname.get(random.nextInt(listOfSurname.size()));
    }

    //генерируем возраст
    private static int personAge(int minAge, int maxAge) {
        return (minAge + random.nextInt(maxAge-minAge + 1));
    }

    //генерируем лист персон
    public static List<Person> listOfPerson(int number, int minAge, int maxAge) {
        List<Person> listOfPerson = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            listOfPerson.add(new Person(PersonGenerate.personName(),
                    PersonGenerate.personSurname(),
                    PersonGenerate.personAge(minAge,maxAge)));
        }
        return listOfPerson;
    }
}
