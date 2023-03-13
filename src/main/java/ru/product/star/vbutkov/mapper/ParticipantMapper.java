package ru.product.star.vbutkov.mapper;

import org.springframework.stereotype.Component;
import ru.product.star.vbutkov.entity.Gender;
import ru.product.star.vbutkov.entity.Participant;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ParticipantMapper {

    public List<Participant> getParticipants(Stream<String> lines) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        return lines.map(line -> {
            var tokens = line.split(",");
            return createParticipant(tokens);
        }).collect(Collectors.toList());
    }

    private Participant createParticipant(String[] tokens) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        var name = tokens[0].strip();
        var gender = Gender.valueOf(tokens[1].strip());
        var distanceToken = tokens[2].strip().split(" ");
        long distance = Long.parseLong(distanceToken[0]);
        var timeToken = tokens[3].strip().split(":");
        var time = LocalTime.of(0, Integer.parseInt(timeToken[0]), Integer.parseInt(timeToken[1]));

        return new Participant(name, gender, distance, time);
    }
}
