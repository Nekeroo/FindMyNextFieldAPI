package org.fmnf.findmynextfieldapi.models.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    @Column(name = "maxplayers")
    private int maxPlayers;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private EventStatus status;

    private String organizer;

}
