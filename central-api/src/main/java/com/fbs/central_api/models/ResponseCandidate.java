package com.fbs.central_api.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseCandidate {
    private ResponseContent content;
    private String finishReason;
    private double avgLogprobs;
}
