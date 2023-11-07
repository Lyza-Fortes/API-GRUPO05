package br.com.api.g5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.entities.Email;
import br.com.api.g5.services.EmailService;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {
    
    @Autowired
    EmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestParam String email, @RequestBody Email emailTo) {
        Email newEmail = emailService.save(emailTo);
        if (newEmail != null) {
            emailService.enviarEmail(newEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body("Email para " + newEmail.getEmail() + " enviado!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enviou nao fiote");
        }
    }
}
