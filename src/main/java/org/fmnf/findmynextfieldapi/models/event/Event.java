package org.fmnf.findmynextfieldapi.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    private int maxPlayers;

    private BigDecimal price;

    private EventStatus status;

    private String organizer;

}
