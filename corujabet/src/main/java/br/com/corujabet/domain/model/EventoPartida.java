package br.com.corujabet.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class EventoPartida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    private LocalDateTime timestamp;
    private String descricao;

    @ManyToOne
    private Time time;

    @ManyToOne
    private Partida partida;
}
