package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collegue;

import java.util.Optional;

public interface CollegueRepo extends JpaRepository<Collegue, Long> {

    Optional<Collegue> findByEmail(String email);
    Optional<Collegue> findById(Long id);
}
