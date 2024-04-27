package Sportify.domain.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Noticia {

    private int id;

    private String titulo;

    private String descripcion;

    private String img;

    private Competicion competicion;

    public Noticia(String titulo, String descripcion, String img, Competicion competicion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
        this.competicion = competicion;
    }
}
