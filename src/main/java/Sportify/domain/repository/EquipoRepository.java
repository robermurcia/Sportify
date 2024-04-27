package Sportify.domain.repository;

import Sportify.domain.entity.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository {

    List<Equipo> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Equipo> find(int id);

    void delete(int id);
}
