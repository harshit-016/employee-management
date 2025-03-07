package com.employee_management.service;

import com.employee_management.entity.EmpEntity;
import com.employee_management.entity.EmpRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpServ {
    @Autowired
    private EmpRepo empRepo;

    public List<EmpEntity> getAllEmp(){
        return empRepo.findAll();
    }
    public EmpEntity saveEmp(EmpEntity empEntity) {

            return empRepo.save(empEntity);

    }

    public EmpEntity getEmpById(Long id) {
        return empRepo.findById(id).orElse(null);
    }

    public void deleteEmp(Long id) {
        empRepo.deleteById(id);
    }

    public EmpEntity updateEmp(@Valid EmpEntity empEntity) {
        // Check if the employee exists by ID
        if (empEntity.getId() != null) {
            EmpEntity existingEmployee = empRepo.findById(empEntity.getId()).orElse(null);

            if (existingEmployee != null) {
                // Update the fields of the existing employee
                existingEmployee.setName(empEntity.getName());
                existingEmployee.setEmail(empEntity.getEmail());
                existingEmployee.setDob(empEntity.getDob());
                existingEmployee.setSalary(empEntity.getSalary());
                existingEmployee.setStatus(empEntity.getStatus());

                // Save the updated employee
                return empRepo.save(existingEmployee);
            } else {
                // Employee with this ID doesn't exist
                throw new IllegalArgumentException("Employee not found with id: " + empEntity.getId());
            }
        } else {
            // ID is null, indicating a new entity (shouldn't happen in update)
            throw new IllegalArgumentException("Employee ID is required for updating");
        }
    }

}
