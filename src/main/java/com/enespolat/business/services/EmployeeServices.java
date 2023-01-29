package com.enespolat.business.services;

import com.enespolat.business.dto.EmployeeDto;
import com.enespolat.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    //CRUD
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws Throwable;
    public ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) throws Throwable;
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id) throws Throwable;


    // model mapper
    public EmployeeDto EntityToDto(EmployeeEntity mployeeEntity);
    public EmployeeEntity DtoToEntity(EmployeeDto employeeDto);



}
