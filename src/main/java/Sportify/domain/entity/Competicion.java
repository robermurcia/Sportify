package Sportify.domain.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Competicion {

    private int id;

    private String nombre;

    private String region;

    private String img;

    private List<Equipo> equipo;

    public Competicion(int id, String nombre, String region, String img) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.img = img;
    }

    public Competicion(String nombre, String region, String img) {
        this.nombre = nombre;
        this.region = region;
        this.img = img;
    }
}
