package dev.raj.filesystem.Controllers;

import dev.raj.filesystem.Dtos.UserDto;
import dev.raj.filesystem.Models.Session;
import dev.raj.filesystem.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/SignUp")
    public String signUp(@RequestBody UserDto userDto){
        userService.signUp(userDto.getEmail(), userDto.getPassword());
        return "Sign Up";
    }
    @PostMapping("/SignIn")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto){
        ResponseEntity<UserDto> response = userService.signIn(userDto.getEmail(), userDto.getPassword());
        return response;
    }

    @PostMapping("/validate")
    public String validate(String token, Long userId){
       Session session =  userService.validate(token, userId);
       if(!session.isActive()){
              return "NOT_ACTIVE";
       }
       return "ACTIVE";

    }

}
