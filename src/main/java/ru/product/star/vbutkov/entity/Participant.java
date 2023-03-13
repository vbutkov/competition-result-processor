package ru.product.star.vbutkov.entity;

import java.time.LocalTime;

public class Participant {
    String name;
    Gender gender;
    long distance;
    LocalTime time;

    public Participant(String name, Gender gender, long distance, LocalTime time) {
        this.name = name;
        this.gender = gender;
        this.distance = distance;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public long getDistance() {
        return distance;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", distance=" + distance + " км" +
                ", time=" + time +
                '}';
    }
}
