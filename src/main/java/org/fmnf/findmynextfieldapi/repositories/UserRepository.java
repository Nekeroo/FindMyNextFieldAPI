package org.fmnf.findmynextfieldapi.repositories;

import org.fmnf.findmynextfieldapi.models.user.UserRegistrationCount;
import org.fmnf.findmynextfieldapi.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    @Query("""
            SELECT new org.fmnf.findmynextfieldapi.models.user.UserRegistrationCount(
                u,
                COUNT(r.id)
            )
            FROM User u
            LEFT JOIN Registration r ON r.user = u
            GROUP BY u
            ORDER BY COUNT(r.id) DESC
        """)
    List<UserRegistrationCount> findUsersWithRegistrationCount();

}
