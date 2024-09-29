package Blog.Blog.Contoller;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.UserRepository;
import Blog.Blog.Security.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registrationForm(){
        return  "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        _User user = registrationForm.toUser(passwordEncoder);
        userRepository.save(user);
        return "redirect:/login";
    }

}
