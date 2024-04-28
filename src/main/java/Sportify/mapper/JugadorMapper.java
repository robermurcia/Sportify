package Sportify.mapper;

import Sportify.controller.model.jugador.JugadorCreateWeb;
import Sportify.controller.model.jugador.JugadorDetailWeb;
import Sportify.controller.model.jugador.JugadorListWeb;
import Sportify.controller.model.jugador.JugadorUpdateWeb;
import Sportify.domain.entity.Jugador;
import Sportify.persistence.model.JugadorEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JugadorMapper {
    JugadorMapper mapper = Mappers.getMapper(JugadorMapper.class);

    @Mapping(target = "equipo", expression = "java(EquipoMapper.mapper.toEquipo(jugadorEntity.getEquipoEntity()))")
    @Named("toJugador")
    Jugador toJugador(JugadorEntity jugadorEntity);

    @Mapping(target = "equipo", ignore = true)
    @IterableMapping(qualifiedByName = "toJugador")
    @Named("toJugadorList")
    List<Jugador> toJugadorList(List<JugadorEntity> jugadorEntities);

    JugadorListWeb toJugadorListWeb(Jugador jugador);

    @Mapping(target = "equipo", expression = "java(EquipoMapper.mapper.toEquipoListWeb(jugador.getEquipo()))")
    JugadorDetailWeb toJugadorDetailWeb(Jugador jugador);

    @Mapping(target = "equipoEntity", expression = "java(EquipoMapper.mapper.toEquipoEntity(jugador.getEquipo()))")
    JugadorEntity toJugadorEntity(Jugador jugador);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipo", ignore = true)
    Jugador toJugador(JugadorCreateWeb jugadorCreateWeb);

    Jugador toJugador(JugadorUpdateWeb jugadorUpdateWeb);
}
