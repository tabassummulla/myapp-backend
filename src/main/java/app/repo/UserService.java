package app.repo;

        import app.model.User;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User add(User user) {
        userRepository.save(user);
        return user;

    }


}
