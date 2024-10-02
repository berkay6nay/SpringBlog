package Blog.Blog.Repository;

import Blog.Blog.Entity.Like;
import Blog.Blog.Entity._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like>  getLikesByPostId(Long postId);

    Like getLikeByUserId(Long userId);

    Like getLikeByUserIdAndPostId(Long userId , Long PostId);
}
