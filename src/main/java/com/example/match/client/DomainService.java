package com.example.match.client;

import com.example.match.api.InquiryCreateCommand;
import com.example.match.api.InquiryScoreCommand;
import com.example.match.query.InquirySummaryView;
import com.example.match.api.InquirySummaryQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author simar bawa
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DomainService implements CommandLineRunner{

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public void run(String ... args) throws Exception {

        UUID id = UUID.randomUUID();

        log.debug("issuing create command");
        commandGateway.sendAndWait(new InquiryCreateCommand(id, "simar", "create"));

        log.debug("issuing score command");
        commandGateway.sendAndWait(new InquiryScoreCommand(id, 5, "complete"));

        log.debug("querying inquiry");
        InquirySummaryView summary = queryGateway.query(new InquirySummaryQuery(id), ResponseTypes.instanceOf(InquirySummaryView.class)).join();
        log.debug("inquiry queried {}", summary);

    }
}
