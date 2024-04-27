package Sportify.domain.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {

    private int id;

    private String nombre;

    private String pais;

    private String img;

    public Equipo(String nombre, String pais, String img) {
        this.nombre = nombre;
        this.pais = pais;
        this.img = img;
    }
}
