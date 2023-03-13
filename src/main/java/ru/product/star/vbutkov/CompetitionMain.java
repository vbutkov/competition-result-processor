package ru.product.star.vbutkov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.product.star.vbutkov.config.CompetitionConfig;
import ru.product.star.vbutkov.entity.Gender;
import ru.product.star.vbutkov.service.ResultsProcessor;

public class CompetitionMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CompetitionConfig.class);
        var competitionProcessor = context.getBean(ResultsProcessor.class);
        var competitionResults = competitionProcessor.readResults("resultsCompetition.csv");
        var fastRunners = competitionProcessor.getFastRunners(competitionResults, 2, 10, Gender.M);
        fastRunners.stream()
                .forEach(System.out::println);

    }
}
