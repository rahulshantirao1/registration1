package com.webAppApi.controller;

import com.webAppApi.payload.RegistrationDto;
import com.webAppApi.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>>getAllRegistrations(){
      return new ResponseEntity<>(  registrationService.getAllRegs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>createRegistration(
            @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        RegistrationDto dtos = registrationService.saveReg(registrationDto);
        return new ResponseEntity<>( dtos,HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(@RequestParam("id")long id){
        registrationService.deleteReg(id);
        return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto>updateRegistration(@PathVariable("id")long id,@RequestBody RegistrationDto registrationDto){
       return new ResponseEntity<>(registrationService.updateReg(id,registrationDto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> findRegistrationById(@PathVariable("id")long id){
       return new ResponseEntity<>( registrationService.findRegById(id),HttpStatus.OK);
    }
}
