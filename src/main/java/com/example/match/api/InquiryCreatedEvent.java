package com.example.match.api;

import lombok.Value;

import java.util.UUID;

/**
 * @author simar bawa
 */

@Value
public class InquiryCreatedEvent {

    UUID id;

    String firstName;
    String status;
}
