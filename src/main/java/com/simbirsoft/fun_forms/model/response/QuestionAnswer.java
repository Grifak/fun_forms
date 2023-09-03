package com.simbirsoft.fun_forms.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionAnswer {
    private String question;
    private String answer;
}
