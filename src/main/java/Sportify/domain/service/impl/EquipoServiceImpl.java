package Sportify.domain.service.impl;

import Sportify.domain.entity.Competicion;
import Sportify.domain.entity.Equipo;
import Sportify.domain.repository.EquipoRepository;
import Sportify.domain.service.CompeticionService;
import Sportify.domain.service.EquipoService;
import Sportify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private CompeticionService competicionService;

    @Override
    public List<Equipo> getAll(Integer page, Integer page_size) {
        return equipoRepository.getAll(page, page_size);
    }

    @Override
    public long getTotalNumberOfRecords(){
        return equipoRepository.getTotalNumberOfRecords();
    }

    @Override
    public Optional<Equipo> find(int id) {
        return equipoRepository.find(id);
    }

    @Override
    public void delete(int id) {
        equipoRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrada con id: " + id));
        equipoRepository.delete(id);
    }

    @Override
    public Equipo create(Equipo equipo, List<Integer> competicionIds) {
        Equipo equipoCreado = equipoRepository.insert(equipo);
        for (Integer competicionId : competicionIds) {
            Competicion competicion = competicionService.find(competicionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + competicionId));
            List<Equipo> equipos = new ArrayList<>(competicion.getEquipo());
            equipos.add(equipoCreado);
            competicion.setEquipo(equipos);
            competicionService.updateEquipos(competicion);
        }
        return equipoCreado;
    }
}
