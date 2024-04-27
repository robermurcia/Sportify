package Sportify.persistence.dao;

import Sportify.persistence.model.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaDAO extends JpaRepository<NoticiaEntity, Integer> {
}
