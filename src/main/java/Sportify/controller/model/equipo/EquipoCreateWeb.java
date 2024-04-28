package Sportify.controller.model.equipo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EquipoCreateWeb {
    private String nombre;

    private String pais;

    private String img;

    private List<Integer> competicionIds;
}
