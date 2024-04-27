package Sportify.persistence.dao;

import Sportify.persistence.model.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoDAO extends JpaRepository<EquipoEntity, Integer> {
}
