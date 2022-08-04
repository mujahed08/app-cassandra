package com.cass.twfive.controller;

import com.cass.twfive.cassandra.LoginRepository;
import org.openapitools.api.UserApi;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController implements UserApi {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public ResponseEntity<List<User>> userAll() {
        final List<User> userAll = new ArrayList<>();
        IntStream.range(97, 122).parallel().forEach(ch -> {
            List<User> users = loginRepository.findByPartKey((byte) ch).parallelStream().map(this::toOut).collect(Collectors.toList());
            userAll.addAll(users);
        });
        return ResponseEntity.ok(userAll);
    }

    User toOut(com.cass.twfive.model.Login login) {
        return new User().id(login.getIdTxt()).updatedAt(login.getUpdatedAt().atOffset(ZoneOffset.UTC)).name(login.getName())
                .email(login.getEmail()).roles(login.getRoles()).username(login.getUsername());
    }
}
