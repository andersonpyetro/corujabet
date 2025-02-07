package br.com.corujabet.domain.event;

import br.com.corujabet.domain.model.PeriodoPartida;
import br.com.corujabet.domain.model.StatusPartida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoPartidaMensagem {
    private Long partidaId;
    private int qntGolTimeDaCasa;
    private int qntGolTimeDeFora;
    private StatusPartida status;
    private PeriodoPartida periodoPartida;
}
