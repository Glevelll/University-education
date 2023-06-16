package org.example;

import lombok.NonNull;

public class NonNullExample extends Person { //оператор проверки нуля
    private String name;

    public NonNullExample(@NonNull Person person) {
        super("Hello");
        this.name = person.getName();
    }
}
