package com.example.kiemtrajpa.repository;

import com.example.kiemtrajpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findAllByEmailAddressAndLastName(String emailAddress , String lastName);
//    Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findAllByFirstNameOrLastName(String firstName , String lastName);
//    Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findAllByLastNameOrderByFirstNameAsc(String firstName);
//    Tìm tất cả các Employee theo fistName không phân biệt hoa thường
    List<Employee> findAllByFirstNameIgnoreCase(String firstName);


}