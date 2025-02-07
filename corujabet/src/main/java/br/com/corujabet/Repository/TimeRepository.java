package br.com.corujabet.Repository;

import br.com.corujabet.domain.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {

    @Query("SELECT t FROM Time t ORDER BY t.pontos DESC, t.golsMarcados DESC")
    List<Time> findAllOrderByRanking();

    Time findByNome(String nome);

    @Query("SELECT t FROM Time t WHERE t.abreviacao = :abreviacao")
    Time findByAbreviacao(@Param("abreviacao") String abreviacao);
}
