package com.enespolat.ui.rest;

import com.enespolat.business.dto.EmployeeDto;
import com.enespolat.business.services.EmployeeServices;
import com.enespolat.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    EmployeeServices employeeServices;

    // Root
    //http://localhost:8080/api/v1/index
    @GetMapping("/index")
    public String getRoot(){
        return "index";
    }

    //LIST
    //http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeServices.getAllEmployees();
        return employeeDtoList;
    }

    //FIND
    //http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws Throwable {
        EmployeeDto employeeDtoList = employeeServices.getEmployeeById(id).getBody();
        return ResponseEntity.ok(employeeDtoList);
    }
    //SAVE
    //http://localhost:8080/api/v1/employees
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        employeeServices.createEmployee(employeeDto);
        return employeeDto;
    }
    //UPDATE
    //http://localhost:8080/api/v1/employees/1
    @PutMapping("/employees/{id}")
    public ResponseEntity <EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        employeeServices.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //DELETE
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map <String, Boolean>> deleteEmployee(@PathVariable Long id) throws Throwable {
        employeeServices.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
