package com.example.kiemtrajpa.service;


import com.example.kiemtrajpa.controller.EmployeeController;
import com.example.kiemtrajpa.entity.Employee;
import com.example.kiemtrajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    // 1.Xem danh sách Employee (có phân trang và sắp xếp theo firstName  giảm dần)
    public Page<Employee> findAllEmployeePage(int page, int pageSize) {
        Page<Employee> employees = employeeRepository.findAll(
                PageRequest.of(page,pageSize,Sort.by("firstName").descending()));
        return employees;
    }




}
