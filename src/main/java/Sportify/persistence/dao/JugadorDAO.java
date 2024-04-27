package Sportify.persistence.dao;

import Sportify.persistence.model.JugadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorDAO extends JpaRepository<JugadorEntity, Integer> {
}
