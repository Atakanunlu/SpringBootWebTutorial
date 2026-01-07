package com.atakanunlu.services;

import com.atakanunlu.dto.EmployeeDTO;
import com.atakanunlu.entities.EmployeeEntity;
import com.atakanunlu.exceptions.ResourceNotFoundException;
import com.atakanunlu.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    public Optional<EmployeeDTO> getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .map(employeeEntity -> modelMapper
                        .map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {

        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);

        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);


    }

    public EmployeeDTO updateEmployeeById(Long employeeId,EmployeeDTO employeeDTO) {

        isExistEmployeeById(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);


    }

    public void isExistEmployeeById(Long employeeId){

        boolean exist = employeeRepository.existsById(employeeId);
        if (!exist) throw new ResourceNotFoundException("Employee not found id: " + employeeId);


    }

    public boolean deleteEmployeeById(Long employeeId) {

        isExistEmployeeById(employeeId);

        employeeRepository.deleteById(employeeId);
        return true;
    }


    public EmployeeDTO updatedPartialEmployeeById(Long employeeId, Map<String, Object> updates) {

        isExistEmployeeById(employeeId);

        //İlk olarak veri tabanından veriyi aldım
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        // mapteki hger değişikliği uyguladım
        updates.forEach((field, value) -> {

            // entity imde ör: email diye alan var mı
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);

            // bu alan erişim izni verdim private olsa bile
            fieldToBeUpdated.setAccessible(true);

            // alan değerini günclledim.
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });

        //güncellenmiş veriyi entitye kaydettim ve dto ya cevirdim.
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
