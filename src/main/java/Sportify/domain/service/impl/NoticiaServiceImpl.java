package Sportify.domain.service.impl;

import Sportify.domain.entity.Noticia;
import Sportify.domain.repository.NoticiaRepository;
import Sportify.domain.service.NoticiaService;
import Sportify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> getAll(Integer page, Integer page_size) {
        return noticiaRepository.getAll(page, page_size);
    }

    @Override
    public long getTotalNumberOfRecords(){
        return noticiaRepository.getTotalNumberOfRecords();
    }

    @Override
    public Optional<Noticia> find(int id) {
        return noticiaRepository.find(id);
    }

    @Override
    public void delete(int id) {
        noticiaRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada con id: " + id));
        noticiaRepository.delete(id);
    }
}
