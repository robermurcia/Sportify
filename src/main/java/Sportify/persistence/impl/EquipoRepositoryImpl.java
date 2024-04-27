package Sportify.persistence.impl;

import Sportify.domain.entity.Equipo;
import Sportify.domain.repository.EquipoRepository;
import Sportify.mapper.EquipoMapper;
import Sportify.persistence.dao.EquipoDAO;
import Sportify.persistence.model.EquipoEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipoRepositoryImpl implements EquipoRepository {

    @Autowired
    EquipoDAO equipoDAO;

    @Override
    public List<Equipo> getAll(Integer page, Integer pageSize) {
        List<EquipoEntity> equipoEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            equipoEntities = equipoDAO.findAll(pageable).stream().toList();
        } else {
            equipoEntities = equipoDAO.findAll();
        }
        return EquipoMapper.mapper.toEquipoList(equipoEntities);
    }

    @Override
    public long getTotalNumberOfRecords() {
        return equipoDAO.count();
    }

    @Override
    public Optional<Equipo> find(int id) {
        EquipoEntity equipoEntity = equipoDAO.findById(id).orElse(null);
        if(equipoEntity == null) {
            return Optional.empty();
        }
        return Optional.of(EquipoMapper.mapper.toEquipo(equipoEntity));
    }

    @Override
    @Transactional
    public void delete(int id) {
        equipoDAO.deleteById(id);
    }
}
