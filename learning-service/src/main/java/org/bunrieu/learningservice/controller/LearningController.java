package org.bunrieu.learningservice.controller;

import jakarta.ws.rs.DefaultValue;
import lombok.RequiredArgsConstructor;
import org.bunrieu.learningservice.dto.QuizQuestionResponse;
import org.bunrieu.learningservice.entity.CharacterCard;
import org.bunrieu.learningservice.repository.CharacterCardRepo;
import org.bunrieu.learningservice.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/learning")
@RequiredArgsConstructor
public class LearningController {
    private final CharacterCardRepo repository;
    private final QuizService quizService;
    //api test
    @GetMapping("/characters")
    public List<CharacterCard> getCharacter(@RequestParam (defaultValue="HIRAGANA")String type){
        return repository.findByType(type);
    }
    @GetMapping("/quiz")
    public List<QuizQuestionResponse> getQuiz(
            @RequestParam(defaultValue = "HIRAGANA") String type,
            @RequestParam(defaultValue = "5") int limit) {
        return quizService.generateQuiz(type, limit);
    }
}
