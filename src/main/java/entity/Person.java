package entity;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public
class Person {
    @Getter(AccessLevel.NONE)
    private static int id_counter;

    @Getter(AccessLevel.NONE)
    private static String[] names = {"Anne", "Bob", "Carol", "David"};

    private String name;
    private int id;
    private int age;

    public Person() {
        Random random = new Random();
        this.name = names[new Random().nextInt(4)];
        this.id = ++id_counter;
        this.age = random.nextInt(99);
    }

}