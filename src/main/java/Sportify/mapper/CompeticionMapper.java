package Sportify.mapper;

import Sportify.controller.model.competicion.CompeticionCreateWeb;
import Sportify.controller.model.competicion.CompeticionDetailWeb;
import Sportify.controller.model.competicion.CompeticionListWeb;
import Sportify.controller.model.competicion.CompeticionUpdateWeb;
import Sportify.controller.model.equipo.EquipoListWeb;
import Sportify.domain.entity.Competicion;
import Sportify.domain.entity.Equipo;
import Sportify.persistence.model.CompeticionEntity;
import Sportify.persistence.model.EquipoEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompeticionMapper {

    CompeticionMapper mapper = Mappers.getMapper(CompeticionMapper.class);

    @Mapping(target = "equipo", expression = "java(mapEquipoEntityToEquipo(competicionEntity.getEquipoEntity()))")
    @Named("toCompeticion")
    Competicion toCompeticion(CompeticionEntity competicionEntity);

    @Mapping(target = "equipo", ignore = true)
    @IterableMapping(qualifiedByName = "toCompeticion")
    @Named("toCompeticionList")
    List<Competicion> toCompeticionList(List<CompeticionEntity> competicionEntities);

    CompeticionListWeb toCompeticionListWeb(Competicion competicion);

    @Mapping(target = "equipos", expression = "java(mapEquipoToEquipoListWeb(competicion.getEquipo()))")
    CompeticionDetailWeb toCompeticionDetailWeb (Competicion competicion);

    @Mapping(target = "equipoEntity", source = "competicion.equipo")
    CompeticionEntity toCompeticionEntity(Competicion competicion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipo", ignore = true)
    Competicion toCompeticion(CompeticionCreateWeb competicionCreateWeb);

    Competicion toCompeticion(CompeticionUpdateWeb competicionUpdateWeb);

    @Named("EquipoEntityToEquipo")
    default List<Equipo> mapEquipoEntityToEquipo(List<EquipoEntity> equipoEntities) {
        return equipoEntities.stream()
                .map(EquipoMapper.mapper::toEquipo)
                .toList();
    }

    @Named("EquipoToEquipoListWeb")
    default List<EquipoListWeb> mapEquipoToEquipoListWeb(List<Equipo> equipos) {
        return equipos.stream()
                .map(EquipoMapper.mapper::toEquipoListWeb)
                .toList();
    }
}
