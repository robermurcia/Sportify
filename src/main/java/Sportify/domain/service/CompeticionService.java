package Sportify.domain.service;


import Sportify.domain.entity.Competicion;

import java.util.List;
import java.util.Optional;

public interface CompeticionService {

    List<Competicion> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Competicion> find(int id);

    void delete(int id);
}
