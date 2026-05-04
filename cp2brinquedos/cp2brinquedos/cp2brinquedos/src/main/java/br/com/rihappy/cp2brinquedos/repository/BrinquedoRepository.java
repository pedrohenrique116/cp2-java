package br.com.rihappy.cp2brinquedos.repository;

import br.com.rihappy.cp2brinquedos.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}