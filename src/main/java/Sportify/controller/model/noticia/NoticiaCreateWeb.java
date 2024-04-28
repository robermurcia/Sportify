package Sportify.controller.model.noticia;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticiaCreateWeb {
    private String titulo;

    private String descripcion;

    private String img;

    private int competicionId;
}
