package Sportify.domain.service;

import Sportify.domain.entity.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {

    List<Jugador> getAll(Integer page, Integer page_size);

    long getTotalNumberOfRecords();

    Optional<Jugador> find(int id);

    void delete(int id);

    Jugador create(Jugador jugador, int equipoId);

    Jugador update(Jugador jugador,int jugadorId, int equipoId);
}
