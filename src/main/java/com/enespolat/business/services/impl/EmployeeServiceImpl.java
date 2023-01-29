package com.enespolat.business.services.impl;

import com.enespolat.business.dto.EmployeeDto;
import com.enespolat.business.services.EmployeeServices;
import com.enespolat.data.entity.EmployeeEntity;
import com.enespolat.data.repository.EmployeeRepository;
import com.enespolat.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    //LIST
    // http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> listDto = new ArrayList<>();
        Iterable<EmployeeEntity> entityList = employeeRepository.findAll();
        for (EmployeeEntity entity : entityList) {
            EmployeeDto employeeDto = EntityToDto(entity);
            listDto.add(employeeDto);
        }
        return listDto;
    }
    //Save
    // http://localhost:8080/api/v1/employees/1
    @PostMapping("/employees")
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=DtoToEntity(employeeDto);
        employeeRepository.save(employeeEntity);
        return employeeDto;
    }

    //FIND
    // http://localhost:8080/api/v1/employees/1
    @GetMapping("/employee/{id}")
    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws Throwable {
        EmployeeEntity employee = (EmployeeEntity) employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Emloyee not exist with id " + id));
        EmployeeDto employeeDto = EntityToDto(employee);
        return ResponseEntity.ok(employeeDto);
    }
    //UPDATE
    // http://localhost:8080/api/v1/employees
    @PutMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDetails) throws Throwable {
        EmployeeEntity employeeEntity = DtoToEntity(employeeDetails); // ModelMapper
        EmployeeEntity employee = (EmployeeEntity) employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " +id));
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setEmailId(employeeEntity.getEmailId());

        EmployeeEntity updatedEmployee = (EmployeeEntity) employeeRepository.save(employee);
        EmployeeDto employeeDto = EntityToDto(employee);
        return ResponseEntity.ok(employeeDto);
    }
    //DELETE
    // http://localhost:8080/api/v1/employees/1
    @DeleteMapping("employees/{id]")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws Throwable {
        EmployeeEntity employee= (EmployeeEntity) employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " +id));
        employeeRepository.deleteById(employee.getId());
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    // Model Mapper
    // Entity ==> DTO
    @Override
    public EmployeeDto EntityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto EmployeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return EmployeeDto;
    }

    // DTO ==> Entity
    @Override
    public EmployeeEntity DtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity EmployeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return EmployeeEntity;
    }
}
