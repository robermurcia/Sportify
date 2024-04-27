package Sportify.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "competiciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompeticionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String region;
    private String img;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "equipo_competicion",
            joinColumns = @JoinColumn(name = "id_competicion"),
            inverseJoinColumns = @JoinColumn(name = "id_equipo")
    )
    private List<EquipoEntity> equipoEntity;
}
