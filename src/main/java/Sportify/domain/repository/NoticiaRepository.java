package Sportify.domain.repository;

import Sportify.domain.entity.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaRepository {

    List<Noticia> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Noticia> find(int id);

    void delete(int id);

    Noticia insert(Noticia noticia);
}
