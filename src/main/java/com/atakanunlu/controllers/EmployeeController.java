package com.atakanunlu.controllers;

import com.atakanunlu.dto.EmployeeDTO;
import com.atakanunlu.entities.EmployeeEntity;
import com.atakanunlu.repositories.EmployeeRepository;
import com.atakanunlu.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/supersecret")
//    public String superSecretMessage(){
//        return "Ben atakan :)";
//    }
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,
                                          @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId){

        return employeeService.deleteEmployeeById(employeeId);

    }

    @PatchMapping
    public EmployeeDTO updatedPartialEmployeeById(Map<String, Object> updates,
                                                  @PathVariable Long employeeId){

        return employeeService.updatedPartialEmployeeById(employeeId,updates);

    }
}
