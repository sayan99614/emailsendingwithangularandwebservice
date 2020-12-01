package com.email.emailapi.controller;

import com.email.emailapi.model.EmailModel;
import com.email.emailapi.model.EmailResponse;
import com.email.emailapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/index")
    public String Hello(){
        return "hello frm intellij";
    }

    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailModel emailModel){
        Boolean status = emailService.sendEmail(emailModel.getSubject(), emailModel.getMessage(), emailModel.getTo());
        if(status){
            return ResponseEntity.ok(new EmailResponse("Email is send successfully"));
        }
        else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not send plz try again"));
        }
    }
}
