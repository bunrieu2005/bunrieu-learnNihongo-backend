package org.bunrieu.learningservice.service;

import lombok.RequiredArgsConstructor;
import org.bunrieu.learningservice.dto.QuizQuestionResponse;
import org.bunrieu.learningservice.entity.CharacterCard;
import org.bunrieu.learningservice.repository.CharacterCardRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final CharacterCardRepo characterCardRepo;
    public List<QuizQuestionResponse> generateQuiz(String type, int numberOfQuestions) {
        List<QuizQuestionResponse> quizList = new ArrayList<>();

        // random from DB
        List<CharacterCard> questions = characterCardRepo.findRandomQuestions(type, numberOfQuestions);

        // 2. creat n option
        for (CharacterCard card : questions) {
            QuizQuestionResponse dto = new QuizQuestionResponse();
            dto.setCharacterJp(card.getCharacterJp());
            dto.setCorrectAnswer(card.getRomaji());

            // get 3 wrong answer
            List<String> options = characterCardRepo.findWrongAnswers(type, card.getRomaji());
            // add correct
            options.add(card.getRomaji());

            // random a,b,c,d
            Collections.shuffle(options);
            dto.setOptions(options);
            quizList.add(dto);
        }

        return quizList;
}
}
