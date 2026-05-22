package org.fmnf.findmynextfieldapi.domain.registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fmnf.findmynextfieldapi.domain.event.Event;
import org.fmnf.findmynextfieldapi.domain.user.User;

@Entity
@Table(name = "registrations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;

}
