package ru.product.star.vbutkov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product.star.vbutkov.entity.Gender;
import ru.product.star.vbutkov.entity.Participant;
import ru.product.star.vbutkov.mapper.ParticipantMapper;
import ru.product.star.vbutkov.service.ResultsProcessor;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultsProcessorTest {

    private ResultsProcessor resultsProcessor;
    private final String RESULTS_FILE = "resultsCompetition.csv";
    private final String RESULTS_FILE_WRONG = "resultsCompetitionWrong.csv";

    private final List<Participant> participants = new ArrayList<>();
    private final List<Participant> fastRunners = new ArrayList<>();

    private final Participant participantF5_1 = new Participant("Participant1", Gender.F, 5L, LocalTime.of(0, 48, 03));
    private final Participant participantF5_2 = new Participant("Participant2", Gender.F, 5L, LocalTime.of(0, 49, 17));
    private final Participant participantF5_3 = new Participant("Participant3", Gender.F, 5L, LocalTime.of(0, 50, 11));
    private final Participant participantF5_4 = new Participant("Participant4", Gender.F, 5L, LocalTime.of(0, 49, 23));
    private final Participant participantF5_5 = new Participant("Participant5", Gender.F, 5L, LocalTime.of(0, 52, 55));

    private final Participant participantM10_1 = new Participant("Participant1", Gender.M, 10L, LocalTime.of(0, 48, 03));
    private final Participant participantM10_2 = new Participant("Participant2", Gender.M, 10L, LocalTime.of(0, 49, 17));
    private final Participant participantM10_3 = new Participant("Participant3", Gender.M, 10L, LocalTime.of(0, 45, 11));
    private final Participant participantM10_4 = new Participant("Participant4", Gender.M, 10L, LocalTime.of(0, 49, 23));
    private final Participant participantM10_5 = new Participant("Participant5", Gender.M, 10L, LocalTime.of(0, 52, 55));


    @BeforeEach
    void buildResultsProcessor() {
        resultsProcessor = new ResultsProcessor(new ParticipantMapper());
    }

    @Test
    void throwExceptionIfCatchException() {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> resultsProcessor.readResults("File not found")),
                () -> assertThrows(IllegalArgumentException.class, () -> resultsProcessor.readResults(RESULTS_FILE_WRONG))
        );
    }

    @Test
    void participantsNotEmptyIfParticipantAddedResults() {
        var participants = resultsProcessor.readResults(RESULTS_FILE);
        assertFalse(participants.isEmpty(), "File resultsCompetition.csv is not be empty");
    }

    @Test
    void participantsSizeIfParticipantAddedResults() {
        var participants = resultsProcessor.readResults(RESULTS_FILE);
        assertEquals(15, participants.size(), () -> "Not correct size list participants");
    }

    @Test
    void getFastRunnersIfGenderFAndDistance5AndLimit3() {
        participants.add(participantF5_1);
        participants.add(participantF5_2);
        participants.add(participantF5_3);
        participants.add(participantF5_4);
        participants.add(participantF5_5);


        fastRunners.add(participantF5_1);
        fastRunners.add(participantF5_2);
        fastRunners.add(participantF5_4);

        var resultsFastRunners = resultsProcessor.getFastRunners(participants, 3, 5, Gender.F);
        assertEquals(resultsFastRunners, fastRunners);
    }

    @Test
    void getFastRunnersIfGenderMAndDistance10AndLimit4() {
        participants.add(participantM10_1);
        participants.add(participantM10_2);
        participants.add(participantM10_3);
        participants.add(participantM10_4);
        participants.add(participantM10_5);

        fastRunners.add(participantM10_3);
        fastRunners.add(participantM10_1);
        fastRunners.add(participantM10_2);
        fastRunners.add(participantM10_4);

        var resultsFastRunners = resultsProcessor.getFastRunners(participants, 4, 10, Gender.M);
        assertEquals(resultsFastRunners, fastRunners);
    }
}