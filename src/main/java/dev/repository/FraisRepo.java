package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.LigneDeFrais;
import dev.domain.Mission;

public interface FraisRepo extends JpaRepository<LigneDeFrais, Integer>
{
	Optional<LigneDeFrais> findById(Integer id);
	List<LigneDeFrais> findByMission(Mission mission);

}
