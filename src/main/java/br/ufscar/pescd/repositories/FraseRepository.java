package br.ufscar.pescd.repositories;

import br.ufscar.pescd.model.FraseConfirmacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraseRepository
        extends JpaRepository<FraseConfirmacao, Long> {
}