package br.com.corujabet.Repository;

import br.com.corujabet.domain.model.Partida;
import br.com.corujabet.domain.model.StatusPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByStatus(StatusPartida status);

    @Query("SELECT p FROM Partida p WHERE p.campeonato.id = :campeonatoId")
    List<Partida> findByCampeonatoId(@Param("campeonatoId") Long campeonatoId);

    @Query("SELECT p FROM Partida p WHERE p.timeDaCasa.id = :timeId OR p.timeDeFora.id = :timeId")
    List<Partida> findByTimeId(@Param("timeId") Long timeId);

    @Modifying
    @Query("UPDATE Partida p SET p.golsTimeDaCasa = :golsCasa, p.golsTimeDeFora = :golsFora WHERE p.id = :partidaId")
    void atualizarPlacar(@Param("partidaId") Long partidaId, @Param("golsCasa") int golsCasa, @Param("golsFora") int golsFora);
}
