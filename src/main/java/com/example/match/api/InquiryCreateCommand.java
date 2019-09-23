package com.example.match.api;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * @author simar bawa
 */

@Value
public class InquiryCreateCommand {

    @TargetAggregateIdentifier
    UUID id;

    String firstName;
    String status;
}
