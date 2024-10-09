package Blog.Blog.Repository;
import Blog.Blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getPostsByUserId(Long userId);
}
