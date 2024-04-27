package Sportify.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "noticias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private String img;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competicion_id")
    private CompeticionEntity competicionEntity;
}
