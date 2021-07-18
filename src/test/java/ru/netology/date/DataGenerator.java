package ru.netology.date;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int days) {
        // Добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        // Добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        List<String> list = Arrays.asList("Архангельск", "Астрахань", "Барнаул", "Владикавказ",
                "Горно-Алтайск", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Краснодар", "Махачкала",
                "Нарьян-Мар", "Салехард", "Самара", "Саранск", "Саратов", "Хабаровск", "Ханты-Мансийск", "Южно-Сахалинск",
                "Великий Новгород", "Владивосток", "Владимир", "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва",
                "Санкт-Петербург", "Ульяновск", "Симферополь", "Ростов-на-Дону");
        Random rand = new Random();
        String city = list.get(rand.nextInt(list.size()));
        return city;
    }

    public static String generateName() {
        // Добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone() {
        // Добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateCityENG()  {
        List<String> list = Arrays.asList("Arkhangelsk", "Astrakhan", "Barnaul", "Vladikavkaz",
                "Gorno-Altaysk", "Yoshkar-Ola", "Kazan", "Kaliningrad", "Kaluga", "Krasnodar", " Makhachkala",
                "Naryan-Mar", "Salekhard", "Samara", "Saransk", "Saratov", "Khabarovsk", "Khanty-Mansiysk", "Yuzhno-Sakhalinsk",
                "The Velikiy Novgorod, Vladivostok, Vladimir", "Ryazan", "Birobidzhan", "Cheboksary", "Moscow", "Saint Petersburg", "Ulyanovsk", "Simferopol", " Rostov-on-Don");
        Random rand = new Random();
        String cityEng = list.get(rand.nextInt(list.size()));
        return cityEng;

    }

    public static String generateNameEng() {
        // Добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale("eng"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }
}