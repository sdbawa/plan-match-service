package com.example.match.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author simar bawa
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquirySummaryView {

    @Id
    UUID id;
    String firstName;
    String initialState;
    String currentState;
    Integer score;
}
