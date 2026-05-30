package br.com.fiap.repository;

import br.com.fiap.model.Fisioterapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Long> {
}