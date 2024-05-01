package Sportify.controller.model.competicion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompeticionCreateWeb {
    private String nombre;

    private String region;

    private String img;

    private List<Integer> equiposId;
}
