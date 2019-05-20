package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Version;

public interface MissionRepo extends JpaRepository<Version, Integer> {
	// to do something with nature
}
