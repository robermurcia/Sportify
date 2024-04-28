package Sportify.domain.repository;


import Sportify.domain.entity.Competicion;

import java.util.List;
import java.util.Optional;

public interface CompeticionRepository {

    List<Competicion> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Competicion> find(int id);

    void delete(int id);

    Competicion insert(Competicion competicion);

    Competicion update(Competicion competicion);

    Competicion updateEquipos(Competicion competicion);
}
