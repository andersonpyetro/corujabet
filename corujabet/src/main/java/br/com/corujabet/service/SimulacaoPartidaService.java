package br.com.corujabet.service;

import br.com.corujabet.Repository.PartidaRepository;
import br.com.corujabet.Repository.TimeRepository;
import br.com.corujabet.domain.event.AtualizacaoPartidaMensagem;
import br.com.corujabet.domain.model.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class SimulacaoPartidaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PartidaRepository partidaRepository;
    private final TimeRepository timeRepository;
    private final Random random = new Random();

    private static final String TOPICO_EVENTOS = "match-events";
    private static final String TOPICO_ATUALIZACOES = "match-updates";

    public SimulacaoPartidaService(KafkaTemplate<String, Object> kafkaTemplate,
                                   PartidaRepository partidaRepository,
                                   TimeRepository timeRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.partidaRepository = partidaRepository;
        this.timeRepository = timeRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void simularEventosPartida() {
        List<Partida> partidasAtivas = partidaRepository.findByStatus(StatusPartida.EM_ANDAMENTO);

        for (Partida partida : partidasAtivas) {
            TipoEvento evento = gerarEventoAleatorio();
            Time timeAtuante = random.nextBoolean() ? partida.getTimeDaCasa() : partida.getTimeDeFora();

            EventoPartida eventoPartida = new EventoPartida();
            eventoPartida.setPartida(partida);
            eventoPartida.setTipoEvento(evento);
            eventoPartida.setTime(timeAtuante);
            eventoPartida.setDescricao(gerarDescricao(evento, timeAtuante));
            eventoPartida.setTimestamp(LocalDateTime.now());

            kafkaTemplate.send(TOPICO_EVENTOS, eventoPartida);

            atualizarEstadoPartida(partida, evento, timeAtuante);
        }
    }

    private TipoEvento gerarEventoAleatorio() {
        TipoEvento[] tipos = TipoEvento.values();
        return tipos[random.nextInt(tipos.length)];
    }

    private String gerarDescricao(TipoEvento evento, Time time) {
        return "O time " + time.getNome() + " registrou o evento " + evento;
    }

    private void atualizarEstadoPartida(Partida partida, TipoEvento evento, Time timeAtuante) {
        if (evento == TipoEvento.GOL) {
            if (timeAtuante.equals(partida.getTimeDaCasa())) {
                partida.setGolsTimeDaCasa(partida.getGolsTimeDaCasa() + 1);
            } else {
                partida.setGolsTimeDeFora(partida.getGolsTimeDeFora() + 1);
            }
        }

        AtualizacaoPartidaMensagem atualizacao = new AtualizacaoPartidaMensagem(
                partida.getId(),
                partida.getGolsTimeDaCasa(),
                partida.getGolsTimeDeFora(),
                partida.getStatus(),
                partida.getPeriodo()
        );

        kafkaTemplate.send(TOPICO_ATUALIZACOES, atualizacao);

        partidaRepository.save(partida);
    }
}
