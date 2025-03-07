package com.employee_management.controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.employee_management.entity.EmpEntity;
import com.employee_management.service.EmpServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmpController {
    @Autowired
    private EmpServ empServ;

    @GetMapping
    public String listEmployees(Model model) {
        List<EmpEntity> employees = empServ.getAllEmp();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new EmpEntity());
        return "employee-form";
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute EmpEntity empEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        System.out.println(empEntity.getId());
        if (empEntity.getId() != null) {
            empServ.updateEmp(empEntity);
        } else {
            empServ.saveEmp(empEntity);
        }

        return "redirect:/employees";
    }


    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        EmpEntity employee = empServ.getEmpById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employee-form";
        } else {
            return "redirect:/employees";
        }
    }



    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        empServ.deleteEmp(id);
        return "redirect:/employees";
    }

}
