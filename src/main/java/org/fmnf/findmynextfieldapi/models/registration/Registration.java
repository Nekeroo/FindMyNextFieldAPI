package org.fmnf.findmynextfieldapi.models.registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fmnf.findmynextfieldapi.models.event.Event;
import org.fmnf.findmynextfieldapi.models.user.User;

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
