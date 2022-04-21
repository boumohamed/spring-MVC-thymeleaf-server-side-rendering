package bouzri.me.springmvc.security.service;

import bouzri.me.springmvc.security.entities.AppRole;
import bouzri.me.springmvc.security.entities.AppUser;
import bouzri.me.springmvc.security.repositories.AppRoleRepository;
import bouzri.me.springmvc.security.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository userRepo;
    private AppRoleRepository roleRepo;
    private PasswordEncoder pe;

    @Override
    public AppUser saveNewUser(String username, String password, String passwordV) {
        if (!password.equals(passwordV)) throw new RuntimeException("Password not match !");
        String PasswordEncoded = pe.encode(password);
        AppUser user = new AppUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(PasswordEncoded);
        user.setActive(true);
        AppUser savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public AppRole saveNewRole(String role, String description) {

        AppRole Role = roleRepo.findByRole(role);
        if (Role != null) throw new RuntimeException("Role Already existed");
        Role = new AppRole();
        Role.setRole(role);
        Role.setDescription(description);
        AppRole savedRole = roleRepo.save(Role);
        return savedRole;
    }


    @Override
    public void addRoleToUser(String username, String role) {
        AppRole Role = roleRepo.findByRole(role);

        if (Role == null) throw new RuntimeException("Role " + role + " Not Founde");
        AppUser User = userRepo.findByUsername(username);
        if (User == null) throw new RuntimeException("User " + username + " Not Found");
        User.getAppRoles().add(Role);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppRole Role = roleRepo.findByRole(role);
        if (Role != null) throw new RuntimeException("Role " + role + " Not Founde");
        AppUser User = userRepo.findByUsername(username);
        if (User != null) throw new RuntimeException("User " + username + " Not Found");
        User.getAppRoles().remove(Role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser User = userRepo.findByUsername(username);
        return User;
    }
}
