package main.demo;

import main.infrastructure.Bag;

public class TestBag {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        numbers.add(10.09);
        numbers.add(63.10);
        for (double d : numbers) {
            System.out.println(d);
        }
    }
}
