package com.thoughtworks.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
//    "timestamp" : "2020-06-17T05:04:07.641+00:00",
//            "error" : "Not Found",
//            "status" : 404,
//            "message" : "Cannot find basic info for user with id 666."
    private String timestamp;
    private String error;
    private String status;
    private String message;
}
