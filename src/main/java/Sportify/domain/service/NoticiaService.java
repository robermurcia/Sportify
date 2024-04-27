package Sportify.domain.service;


import Sportify.domain.entity.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaService {

    List<Noticia> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Noticia> find(int id);

    void delete(int id);
}
