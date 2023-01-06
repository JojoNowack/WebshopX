package de.hsa.OOSD.WebshopX.webshopx.repositories;

import de.hsa.OOSD.WebshopX.webshopx.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
