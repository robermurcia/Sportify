package Sportify.controller.model.jugador;

import Sportify.controller.model.equipo.EquipoListWeb;
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
public class JugadorDetailWeb {
    private int id;

    private String nombre;

    private String apellidos;

    private int edad;

    private String nacionalidad;

    private String posicion;

    private double precio;

    private String img;

    private EquipoListWeb equipo;
}
