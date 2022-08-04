package com.cass.twfive.controller;

import com.cass.twfive.IdentifierService;
import com.cass.twfive.cassandra.LoginRepository;
import com.cass.twfive.model.Login;
import com.cass.twfive.utils.AppUtils;
import org.openapitools.api.AuthApi;
import org.openapitools.model.MessageResponse;
import org.openapitools.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController implements AuthApi {


    @Autowired
    LoginRepository loginRepository;

    @Autowired
    IdentifierService identifierService;

    @Override
    public ResponseEntity<MessageResponse> signup(SignupRequest signupRequest) {

        byte partKey = AppUtils.charAtZeroAsPartKey(signupRequest.getUsername().toLowerCase());
        loginRepository.findById(new BasicMapId()
            .with("partKey", partKey)
            .with("username", signupRequest.getUsername())
        )
        .ifPresent(l -> {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username Already Taken");
        });

        com.cass.twfive.model.Identifier identifier = identifierService.getNextIdentifier("login", "sys");

        com.cass.twfive.model.Login login = Login.builder()
            .partKey(partKey)
            .username(signupRequest.getUsername())
            .password(signupRequest.getPassword())
            .name(signupRequest.getName())
            .email(signupRequest.getEmail())
            .registeredOn(LocalDateTime.now())
            .dob(LocalDate.of(1990, 7, 8))
            .roles(signupRequest.getRoles())
            .id(identifier.getId())
            .suffix("L")
            .prefix("M")
            .idTxt(String.join("", "M", identifier.getId().toString(), "L"))
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .appId((byte) 100)
            .build();

        Login saved = loginRepository.save(login);

        return ResponseEntity.accepted()
                .body(new MessageResponse().message("User registered successfully").identifier(saved.getIdTxt()));
    }
}