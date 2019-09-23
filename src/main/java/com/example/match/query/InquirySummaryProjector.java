package com.example.match.query;

import com.example.match.api.InquiryCreatedEvent;
import com.example.match.api.InquiryScoredEvent;
import com.example.match.api.InquirySummaryQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * @author simar bawa
 */

@Component
@RequiredArgsConstructor
@Slf4j
//@Profile("query")
public class InquirySummaryProjector {

    private final EntityManager entityManager;

    @EventHandler
    public void on(InquiryCreatedEvent evt) {
        log.debug("projecting evt {}", evt);
        entityManager.persist(new InquirySummaryView(evt.getId(), evt.getFirstName(), evt.getStatus(), evt.getStatus(), 0));
    }

    @EventHandler
    public void on(InquiryScoredEvent evt) {
        log.debug("projecting evt {}", evt);
        InquirySummaryView summary = entityManager.find(InquirySummaryView.class, evt.getId());
        summary.score = evt.getScore();
        summary.currentState = evt.getStatus();
    }

    @QueryHandler
    public InquirySummaryView handle(InquirySummaryQuery qry) {
        return entityManager.find(InquirySummaryView.class, qry.getId());
    }

}
