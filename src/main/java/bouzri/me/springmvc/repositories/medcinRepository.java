package bouzri.me.springmvc.repositories;

import bouzri.me.springmvc.entities.Medcin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface medcinRepository extends JpaRepository<Medcin, Long> {
}
