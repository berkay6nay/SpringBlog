package Blog.Blog.Security;

import Blog.Blog.Entity._User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String email;

    public _User toUser(PasswordEncoder passwordEncoder){
        return new _User(
                username , passwordEncoder.encode(password) , email
        );
    }
}
