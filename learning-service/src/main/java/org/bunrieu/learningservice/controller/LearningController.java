package org.bunrieu.learningservice.controller;

import lombok.RequiredArgsConstructor;
import org.bunrieu.learningservice.dto.QuizQuestionResponse;
import org.bunrieu.learningservice.entity.CharacterCard;
import org.bunrieu.learningservice.repository.CharacterCardRepo;
import org.bunrieu.learningservice.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/random-by-row")
    public CharacterCard getRandomCharacterByRow(@RequestParam String rowName) {
        return repository.findRandomByRowName(rowName);
    }

    // Debug endpoint - Health check
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "learning-service");
        response.put("totalCharacters", repository.count());
        return ResponseEntity.ok(response);
    }

    // Debug endpoint - Check database by rowName
    @GetMapping("/debug/rows")
    public ResponseEntity<Map<String, Object>> debugRows(@RequestParam(required = false) String rowName) {
        Map<String, Object> response = new HashMap<>();
        
        if (rowName == null) {
            // List all available rows
            List<CharacterCard> allCards = repository.findAll();
            response.put("totalCards", allCards.size());
            response.put("message", "Database has " + allCards.size() + " characters");
            response.put("rows", allCards.stream().map(c -> c.getRowName()).distinct().toList());
        } else {
            // Check specific row
            CharacterCard card = repository.findRandomByRowName(rowName);
            response.put("rowName", rowName);
            response.put("found", card != null);
            if (card != null) {
                response.put("character", card.getCharacterJp());
                response.put("romaji", card.getRomaji());
            }
        }
        
        return ResponseEntity.ok(response);
    }
}
