package com.atakanunlu.controllers;

import com.atakanunlu.dto.EmployeeDTO;
import com.atakanunlu.entities.EmployeeEntity;
import com.atakanunlu.repositories.EmployeeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/supersecret")
//    public String superSecretMessage(){
//        return "Ben atakan :)";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello from put";
    }

}
