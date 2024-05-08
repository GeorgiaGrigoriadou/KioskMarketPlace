package com.georgiagrig.kioskmarketplace.database.factories;

import com.georgiagrig.kioskmarketplace.entities.Quiz;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Component
public abstract class BaseFactory<T> {
    protected final Faker faker = new Faker(new Locale("en-US"));

    public abstract T make();

    public Iterable<T> make(int rows) {
        ArrayList<T> items = new ArrayList<>();

        for (int i=0;i<rows;i++) {
            items.add(make());
        }

        return items;
    }
}
