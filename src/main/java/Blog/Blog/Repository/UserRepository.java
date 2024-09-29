package Blog.Blog.Repository;


import Blog.Blog.Entity._User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<_User, Long> {
    _User findByUsername(String username);
}
