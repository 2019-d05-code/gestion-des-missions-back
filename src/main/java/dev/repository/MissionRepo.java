package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.domain.Collegue;
import dev.domain.Mission;

public interface MissionRepo extends JpaRepository<Mission, Integer> {

	List<Mission> findByCollegue(Collegue collegue);

	@Query("Select m from Mission m order by prime desc")
	List<Mission> sortAllMissionDesc();

	@Query("Select m from Mission m order by prime Asc")
	List<Mission> sortAllMissionAsc();

}
