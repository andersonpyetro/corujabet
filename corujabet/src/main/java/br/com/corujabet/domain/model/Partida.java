package br.com.corujabet.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Time timeDaCasa;

    @ManyToOne
    private Time timeDeFora;

    private int golsTimeDaCasa;
    private int golsTimeDeFora;

    @Enumerated(EnumType.STRING)
    private StatusPartida status;

    @Enumerated(EnumType.STRING)
    private PeriodoPartida periodo;

    @OneToMany(mappedBy = "partida")
    private List<EventoPartida> eventos;

    @ManyToOne
    private Campeonato campeonato;
}
