package product.star.vbutkov;

import product.star.vbutkov.service.ParticipantService;
import product.star.vbutkov.entity.Gender;

public class CompetitionMain {
    public static void main(String[] args) {

        ResultsProcessor resultsProcessor = new ResultsProcessor(new ParticipantService());

        var participants = resultsProcessor.loadResultsCompetition("resultsCompetition.csv");

        resultsProcessor.getFastRunners(participants, 3, 5, Gender.M);
    }
}
