package ru.product.star.vbutkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.product.star.vbutkov.mapper.ParticipantMapper;
import ru.product.star.vbutkov.entity.Gender;
import ru.product.star.vbutkov.entity.Participant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

@Service
public class ResultsProcessor {

    ParticipantMapper participantService;

    @Autowired
    public ResultsProcessor(ParticipantMapper resultParticipant) {
        this.participantService = resultParticipant;
    }

    public List<Participant> readResults(String fileName) {
        List<Participant> participants;
        var path = getClass().getClassLoader().getResource(fileName);
        if (path == null) {
            throw new RuntimeException("File not found: " + fileName);
        }
        try (var lines = Files.lines(Path.of(path.getPath()))) {
            participants = participantService.getParticipants(lines);
        } catch (IOException e) {
            throw new RuntimeException("Error IO with file: " + fileName);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalArgumentException("File format: " + fileName + " is incorrect");
        }

        return participants;
    }

    public List<Participant> getFastRunners(List<Participant> participants, int limit, long distance, Gender gender) {
        return participants.stream()
                .filter(participant ->
                        participant.getDistance() == distance && participant.getGender() == gender)
                .sorted(Comparator.comparing(Participant::getTime))
                .limit(limit)
                .collect(Collectors.toList());
    }

}
