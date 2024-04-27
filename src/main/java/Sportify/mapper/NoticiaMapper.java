package Sportify.mapper;

import Sportify.controller.model.noticia.NoticiaDetailWeb;
import Sportify.controller.model.noticia.NoticiaListWeb;
import Sportify.domain.entity.Noticia;
import Sportify.persistence.model.NoticiaEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoticiaMapper {

    NoticiaMapper mapper = Mappers.getMapper(NoticiaMapper.class);

    @Mapping(target = "competicion", expression = "java(CompeticionMapper.mapper.toCompeticion(noticiaEntity.getCompeticionEntity()))")
    @Named("toNoticia")
    Noticia toNoticia(NoticiaEntity noticiaEntity);

    @Mapping(target = "competicion", ignore = true)
    @IterableMapping(qualifiedByName = "toNoticia")
    @Named("toNoticiaList")
    List<Noticia> toNoticiaList(List<NoticiaEntity> noticiaEntities);

    NoticiaListWeb toNoticiaListWeb(Noticia noticia);

    @Mapping(target = "competicion", expression = "java(CompeticionMapper.mapper.toCompeticionListWeb(noticia.getCompeticion()))")
    NoticiaDetailWeb toNoticiaDetailWeb(Noticia noticia);
}
