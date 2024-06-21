package dev.raj.filesystem.Services;

import dev.raj.filesystem.Dtos.UserDto;
import dev.raj.filesystem.Models.Session;
import dev.raj.filesystem.Models.User;
import dev.raj.filesystem.Repository.SessionRepository;
import dev.raj.filesystem.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.Map;

@Service
public class UserService {

    UserRepository userRepository;
    SessionRepository sessionRepository;
    public UserService(UserRepository userRepository, SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }
    public void signUp(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        System.out.println("User signed up successfully");
    }





String token  = "token";

    public ResponseEntity<UserDto> signIn(String email, String password){

        User user =   userRepository.findByEmailAndPassword(email, password);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        if(user!=null){
            map.add("AUTH_TOKEN", token);
            map.add("USER_ID", user.getId().toString());
            Session session = new Session();
            session.setToken(token);
            session.setUserId(user.getId());
            session.setActive(true);
            sessionRepository.save(session);
        }
            UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(
                userDto,
                map,
                HttpStatus.OK);
       return responseEntity;
    }
    public Session  validate(String token, Long userId){
       Session session =  sessionRepository.findSessionByUserIdAndToken(userId, token);
        return session;
    }
}
