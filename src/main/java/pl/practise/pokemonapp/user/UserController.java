package pl.practise.pokemonapp.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User singUp(@RequestBody User user){
        return userService.addUser(user);
    }
}