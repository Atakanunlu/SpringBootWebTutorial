package com.atakanunlu.controllers;

import com.atakanunlu.dto.EmployeeDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/supersecret")
//    public String superSecretMessage(){
//        return "Ben atakan :)";
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id,"ATAKAN","atakan@gmail.com",24, LocalDate.of(2026,01,03),true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Merhaba " + age + " " + sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.getId(100L);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello from put";
    }

}
