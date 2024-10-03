package Blog.Blog.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private _User user;

    @ManyToOne
    private Post post;

    @Column(length = 1000)
    private String commentBody;

    private Date date = new Date();

}
