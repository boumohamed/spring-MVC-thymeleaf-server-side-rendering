package bouzri.me.springmvc.security.repositories;

import bouzri.me.springmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRole(String role);
}
