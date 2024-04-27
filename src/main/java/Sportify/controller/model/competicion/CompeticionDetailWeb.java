package Sportify.controller.model.competicion;

import Sportify.controller.model.equipo.EquipoListWeb;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompeticionDetailWeb {
    private int id;

    private String nombre;

    private String region;

    private String img;

    private List<EquipoListWeb> equipos;
}
