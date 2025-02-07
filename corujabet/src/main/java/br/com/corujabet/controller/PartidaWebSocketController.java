package br.com.corujabet.controller;

import br.com.corujabet.domain.event.AtualizacaoPartidaMensagem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PartidaWebSocketController {

    @MessageMapping("/partidas")
    @SendTo("/topic/live-matches")
    public AtualizacaoPartidaMensagem enviarAtualizacaoPartida(AtualizacaoPartidaMensagem atualizacao) {
        return atualizacao;
    }
}
