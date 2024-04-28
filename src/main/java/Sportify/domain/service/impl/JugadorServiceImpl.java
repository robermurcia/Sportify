package Sportify.domain.service.impl;

import Sportify.domain.entity.Equipo;
import Sportify.domain.entity.Jugador;
import Sportify.domain.repository.EquipoRepository;
import Sportify.domain.repository.JugadorRepository;
import Sportify.domain.service.JugadorService;
import Sportify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Jugador> getAll(Integer page, Integer page_size) {
        return jugadorRepository.getAll(page, page_size);
    }

    @Override
    public long getTotalNumberOfRecords(){
        return jugadorRepository.getTotalNumberOfRecords();
    }

    @Override
    public Optional<Jugador> find(int id) {
        return jugadorRepository.find(id);
    }

    @Override
    public void delete(int id) {
        jugadorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + id));
        jugadorRepository.delete(id);
    }

    @Override
    public Jugador create(Jugador jugador, int equipoId) {
        Equipo equipo = equipoRepository.find(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + equipoId));
        jugador.setEquipo(equipo);
        return jugadorRepository.insert(jugador);
    }

    @Override
    public Jugador update(Jugador jugador,int jugadorId, int equipoId) {
        jugador.setId(jugadorId);
        Equipo equipo = equipoRepository.find(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + equipoId));
        jugador.setEquipo(equipo);
        return jugadorRepository.insert(jugador);
    }
}
