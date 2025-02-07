package br.com.corujabet.consumer;

import br.com.corujabet.domain.event.AtualizacaoPartidaMensagem;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorAtualizacaoPartida {

    private final SimpMessagingTemplate template;

    public ConsumidorAtualizacaoPartida(SimpMessagingTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "match-updates", groupId = "grupo-partida")
    public void consumirAtualizacaoPartida(AtualizacaoPartidaMensagem atualizacao) {
        template.convertAndSend("/topic/live-matches", atualizacao);
    }
}
