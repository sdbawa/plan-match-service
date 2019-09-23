package com.example.match.command;

import com.example.match.api.InquiryCreateCommand;
import com.example.match.api.InquiryCreatedEvent;
import com.example.match.api.InquiryScoreCommand;
import com.example.match.api.InquiryScoredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

/**
 * @author simar bawa
 */

@Aggregate
@RequiredArgsConstructor
@Slf4j
//@Profile("command")
public class Inquiry {

    @AggregateIdentifier
    UUID id;

    String firstName;
    Integer score;
    String status;

    @CommandHandler
    public Inquiry(InquiryCreateCommand cmd) {
        log.debug("handling cmd {}", cmd);
        if (cmd.getFirstName().equalsIgnoreCase("invalid")) throw new IllegalArgumentException("name is invalid");
        AggregateLifecycle.apply(new InquiryCreatedEvent(cmd.getId(), cmd.getFirstName(), cmd.getStatus()));
    }


    @CommandHandler
    public void handle(InquiryScoreCommand cmd) {
        log.debug("handling cmd {}", cmd);
        if (cmd.getScore() <= 0) throw new IllegalArgumentException("score <= 0");
        AggregateLifecycle.apply(new InquiryScoredEvent(cmd.getId(), cmd.getScore(), cmd.getStatus()));
    }

    @EventSourcingHandler
    public void on(InquiryCreatedEvent evt) {
        log.debug("applying evt {}", evt);
        id = evt.getId();
        firstName = evt.getFirstName();
        status = evt.getStatus();
    }

    @EventSourcingHandler
    public void on(InquiryScoredEvent evt) {
        log.debug("applying evt {}", evt);
        score = evt.getScore();
        status = evt.getStatus();
    }
}
