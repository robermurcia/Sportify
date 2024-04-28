package Sportify.mapper;

import Sportify.controller.model.equipo.EquipoCreateWeb;
import Sportify.controller.model.equipo.EquipoDetailWeb;
import Sportify.controller.model.equipo.EquipoListWeb;
import Sportify.domain.entity.Equipo;
import Sportify.persistence.model.EquipoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipoMapper {
    EquipoMapper mapper = Mappers.getMapper(EquipoMapper.class);

    Equipo toEquipo(EquipoEntity equipoEntity);

    List<Equipo> toEquipoList(List<EquipoEntity> equipoEntities);

    EquipoListWeb toEquipoListWeb(Equipo equipo);

    EquipoDetailWeb toEquipoDetailWeb(Equipo equipo);

    EquipoEntity toEquipoEntity(Equipo equipo);

    @Mapping(target = "id", ignore = true)
    Equipo toEquipo(EquipoCreateWeb equipoCreateWeb);
}
