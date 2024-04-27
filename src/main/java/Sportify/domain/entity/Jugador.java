package Sportify.domain.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {

    private int id;

    private String nombre;

    private String apellidos;

    private int edad;

    private String nacionalidad;

    private String posicion;

    private double precio;

    private String img;

    private Equipo equipo;

    public Jugador(String nombre, String apellidos, int edad, String nacionalidad, String posicion, double precio, String img, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.precio = precio;
        this.img = img;
        this.equipo = equipo;
    }
}
