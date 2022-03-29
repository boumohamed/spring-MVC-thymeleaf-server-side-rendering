package bouzri.me.springmvc.repositories;

import bouzri.me.springmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String key, Pageable pageable);
}
