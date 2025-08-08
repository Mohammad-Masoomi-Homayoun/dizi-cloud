package nl.masoomi.authorization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepository extends UserDetailsService, CrudRepository<User, Long> {

    User findByUsername(String username);

    @Override
    default User loadUserByUsername(String username) {
        return findByUsername(username);
    }
}
