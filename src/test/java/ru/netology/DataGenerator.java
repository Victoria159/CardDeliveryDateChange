package ru.netology;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    final static String[] federalCities = {"Москва", "Омск", "Екатеринбург", "Казань", "Курск"};


    public static DataGeneratorForm generateData() {
        Faker faker = new Faker(new Locale("ru"));
        Random rndCities = new Random();
        String cityName = federalCities[rndCities.nextInt(federalCities.length)];
        String[] customerName = faker.name().name().split(" ");
        return new DataGeneratorForm(cityName,
                customerName[0] + " " + customerName[1],
                faker.phoneNumber().cellPhone());
    }

    public static String getDate(int addDays) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        LocalDate date = LocalDate.now().plusDays(addDays);
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}

