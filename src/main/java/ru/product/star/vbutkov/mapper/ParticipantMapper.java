package ru.product.star.vbutkov.service;

import ru.product.star.vbutkov.entity.Gender;
import ru.product.star.vbutkov.entity.Participant;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParticipantService {

    public List<Participant> getParticipants(Stream<String> lines) {
        return lines.map(line -> {
            var tokens = line.split(",");
            return createParticipant(tokens);
        }).collect(Collectors.toList());
    }

    private Participant createParticipant(String[] tokens) {
        try {
            var name = tokens[0].strip();
            var gender = Gender.valueOf(tokens[1].strip());
            var distanceToken = tokens[2].strip().split(" ");
            long distance = Long.parseLong(distanceToken[0]);
            var timeToken = tokens[3].strip().split(":");
            var time = LocalTime.of(0, Integer.parseInt(timeToken[0]), Integer.parseInt(timeToken[1]));

            return new Participant(name, gender, distance, time);
        } catch (Exception e) {
            throw new RuntimeException("ParseException: " + e.getMessage());
        }
    }
}
