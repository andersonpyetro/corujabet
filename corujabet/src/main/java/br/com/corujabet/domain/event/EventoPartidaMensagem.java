package br.com.corujabet.domain.event;

import br.com.corujabet.domain.model.TipoEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoPartidaMensagem {
    private Long partidaId;
    private TipoEvento tipo;
    private String time;
    private String descricao;
    private LocalDateTime timestamp;
}
