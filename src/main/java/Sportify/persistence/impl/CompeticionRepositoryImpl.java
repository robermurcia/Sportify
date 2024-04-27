package Sportify.persistence.impl;

import Sportify.domain.entity.Competicion;
import Sportify.domain.repository.CompeticionRepository;
import Sportify.mapper.CompeticionMapper;
import Sportify.persistence.dao.CompeticionDAO;
import Sportify.persistence.model.CompeticionEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompeticionRepositoryImpl implements CompeticionRepository {

    @Autowired
    CompeticionDAO competicionDAO;

    @Override
    public List<Competicion> getAll(Integer page, Integer pageSize) {
        List<CompeticionEntity> competicionEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            competicionEntities = competicionDAO.findAll(pageable).stream().toList();
        } else {
            competicionEntities = competicionDAO.findAll();
        }
        return CompeticionMapper.mapper.toCompeticionList(competicionEntities);
    }

    @Override
    public long getTotalNumberOfRecords() {
        return competicionDAO.count();
    }

    @Override
    public Optional<Competicion> find(int id) {
        CompeticionEntity competicionEntity = competicionDAO.findById(id).orElse(null);
        if(competicionEntity == null) {
            return Optional.empty();
        }
        return Optional.of(CompeticionMapper.mapper.toCompeticion(competicionEntity));
    }
    @Override
    @Transactional
    public void delete(int id) {
        competicionDAO.deleteById(id);
    }
}
