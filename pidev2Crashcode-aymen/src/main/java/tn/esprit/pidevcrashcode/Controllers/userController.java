package tn.esprit.pidevcrashcode.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Services.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")


public class userController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User u) {
        return userService.addUser(u);
    }
    @PutMapping("/updateUser/{id}")
    public User updateUser(@RequestBody User u,@PathVariable Integer id) {
        return userService.updateUser(u, id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/get/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
