package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Mission;

public interface PrimeRepo extends JpaRepository<Mission, Integer> {

	@Query("Select m from Mission m order by prime desc")
	List<Mission> sortAllMissionDesc();

	@Query("Select m from Mission m order by prime Asc")
	List<Mission> sortAllMissionAsc();
}
