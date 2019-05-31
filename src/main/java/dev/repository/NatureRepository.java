/**
 *
 */
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Nature;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface NatureRepository extends JpaRepository<Nature, Integer> {

}
