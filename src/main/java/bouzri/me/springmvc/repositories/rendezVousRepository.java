package bouzri.me.springmvc.repositories;

import bouzri.me.springmvc.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rendezVousRepository extends JpaRepository<RendezVous, Long> {
}
