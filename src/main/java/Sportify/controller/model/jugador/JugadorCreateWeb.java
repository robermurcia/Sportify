package Sportify.controller.model.jugador;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JugadorCreateWeb {
    private String nombre;

    private String apellidos;

    private int edad;

    private String nacionalidad;

    private String posicion;

    private double precio;

    private String img;

    private int equipoId;
}
