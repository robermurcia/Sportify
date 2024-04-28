package Sportify.domain.service.impl;

import Sportify.domain.entity.Competicion;
import Sportify.domain.entity.Equipo;
import Sportify.domain.repository.CompeticionRepository;
import Sportify.domain.repository.EquipoRepository;
import Sportify.domain.service.CompeticionService;
import Sportify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompeticionServiceImpl implements CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Competicion> getAll(Integer page, Integer page_size) { return competicionRepository.getAll(page, page_size);
    }

    @Override
    public long getTotalNumberOfRecords(){
        return competicionRepository.getTotalNumberOfRecords();
    }

    @Override
    public Optional<Competicion> find(int id) {
        return competicionRepository.find(id);
    }

    @Override
    public void delete(int id) {
        competicionRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + id));
        competicionRepository.delete(id);
    }

    @Override
    public Competicion create(Competicion competicion, List<Integer> equipoId) {
        List<Equipo> equipos = equipoId.stream()
                .map(equipo -> equipoRepository.find(equipo)
                        .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + equipoId))
                )
                .toList();
        competicion.setEquipo(equipos);
        return competicionRepository.insert(competicion);
    }

    @Override
    public Competicion update(Competicion competicion,int competicionId, List<Integer> equipoId) {
        competicion.setId(competicionId);
        List<Equipo> equipos = equipoId.stream()
                .map(equipo -> equipoRepository.find(equipo)
                        .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + equipoId))
                )
                .toList();
        competicion.setEquipo(equipos);
        return competicionRepository.insert(competicion);
    }

    @Override
    public Competicion updateEquipos(Competicion competicion) {
        return competicionRepository.update(competicion);
    }
}
