package Sportify.controller.model.jugador;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JugadorUpdateWeb {

    private int edad;

    private String nacionalidad;

    private String posicion;

    private double precio;

    private int equipoId;
}
