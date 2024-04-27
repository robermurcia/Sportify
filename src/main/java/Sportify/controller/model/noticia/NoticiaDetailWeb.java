package Sportify.controller.model.noticia;

import Sportify.controller.model.competicion.CompeticionListWeb;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticiaDetailWeb {
    private int id;

    private String titulo;

    private String descripcion;

    private String img;

    private CompeticionListWeb competicion;
}
