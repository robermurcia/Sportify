package Sportify.domain.repository;

import Sportify.domain.entity.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorRepository {

    List<Jugador> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Jugador> find(int id);

    void delete(int id);

    Jugador insert(Jugador jugador);

    Jugador update(Jugador jugador);
}
