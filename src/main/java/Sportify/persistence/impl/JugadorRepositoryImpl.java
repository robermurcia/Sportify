package Sportify.persistence.impl;

import Sportify.domain.entity.Jugador;
import Sportify.domain.repository.JugadorRepository;
import Sportify.mapper.JugadorMapper;
import Sportify.persistence.dao.JugadorDAO;
import Sportify.persistence.model.JugadorEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JugadorRepositoryImpl implements JugadorRepository {

    @Autowired
    JugadorDAO jugadorDAO;

    @Override
    public List<Jugador> getAll(Integer page, Integer pageSize) {
        List<JugadorEntity> jugadorEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            jugadorEntities = jugadorDAO.findAll(pageable).stream().toList();
        } else {
            jugadorEntities = jugadorDAO.findAll();
        }
        return JugadorMapper.mapper.toJugadorList(jugadorEntities);
    }

    @Override
    public long getTotalNumberOfRecords() {
        return jugadorDAO.count();
    }

    @Override
    public Optional<Jugador> find(int id) {
        JugadorEntity jugadorEntity = jugadorDAO.findById(id).orElse(null);
        if(jugadorEntity == null) {
            return Optional.empty();
        }
        return Optional.of(JugadorMapper.mapper.toJugador(jugadorEntity));
    }
    @Override
    @Transactional
    public void delete(int id) {
        jugadorDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Jugador insert(Jugador jugador) {
        JugadorEntity jugadorEntity = jugadorDAO.save(JugadorMapper.mapper.toJugadorEntity(jugador));
        return JugadorMapper.mapper.toJugador(jugadorEntity);
    }

    @Override
    public Jugador update(Jugador jugador) {
        JugadorEntity jugadorEntity = jugadorDAO.save(JugadorMapper.mapper.toJugadorEntity(jugador));
        return JugadorMapper.mapper.toJugador(jugadorEntity);
    }
}
