package org.bunrieu.learningservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizQuestionResponse {
    private String characterJp; //?a,b
    private String correctAnswer;//a:correct,b:incorrect
    private List<String> options; //a,b,c,d 4 options
}
