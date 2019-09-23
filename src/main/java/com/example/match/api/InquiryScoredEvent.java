package com.example.match.api;

import lombok.Value;

import java.util.UUID;

/**
 * @author simar bawa
 */

@Value
public class InquiryScoredEvent {

    UUID id;

    Integer score;
    String status;
}
