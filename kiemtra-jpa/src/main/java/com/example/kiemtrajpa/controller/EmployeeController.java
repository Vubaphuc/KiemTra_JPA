package com.example.kiemtrajpa.controller;


import com.example.kiemtrajpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee")
    public ResponseEntity<?> findAllEmployeePage(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(employeeService.findAllEmployeePage(page,pageSize));
    }
}
