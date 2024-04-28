package Sportify.persistence.impl;

import Sportify.domain.entity.Noticia;
import Sportify.domain.repository.NoticiaRepository;
import Sportify.mapper.NoticiaMapper;
import Sportify.persistence.dao.NoticiaDAO;
import Sportify.persistence.model.NoticiaEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoticiaRepositoryImpl implements NoticiaRepository {

    @Autowired
    NoticiaDAO noticiaDAO;

    @Override
    public List<Noticia> getAll(Integer page, Integer pageSize) {
        List<NoticiaEntity> noticiaEntities;
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            noticiaEntities = noticiaDAO.findAll(pageable).stream().toList();
        } else {
            noticiaEntities = noticiaDAO.findAll();
        }
        return NoticiaMapper.mapper.toNoticiaList(noticiaEntities);
    }

    @Override
    public long getTotalNumberOfRecords() { return noticiaDAO.count();
    }

    @Override
    public Optional<Noticia> find(int id) {
        NoticiaEntity noticiaEntity = noticiaDAO.findById(id).orElse(null);
        if(noticiaEntity == null) {
            return Optional.empty();
        }
        return Optional.of(NoticiaMapper.mapper.toNoticia(noticiaEntity));
    }
    @Override
    @Transactional
    public void delete(int id) {
        noticiaDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Noticia insert(Noticia noticia) {
        NoticiaEntity noticiaEntity = noticiaDAO.save(NoticiaMapper.mapper.toNoticiaEntity(noticia));
        return NoticiaMapper.mapper.toNoticia(noticiaEntity);
    }
}
