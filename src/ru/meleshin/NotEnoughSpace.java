package ru.meleshin;

public class NotEnoughSpace extends Exception{

    public NotEnoughSpace(String message) {
        super(message);
    }
}
