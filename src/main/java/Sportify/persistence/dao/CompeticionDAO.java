package Sportify.persistence.dao;

import Sportify.persistence.model.CompeticionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompeticionDAO extends JpaRepository<CompeticionEntity, Integer> {
}
