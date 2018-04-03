package com.example.automapping;


import com.example.automapping.dto.EmployeeDto;
import com.example.automapping.model.Employee;
import com.example.automapping.service.EmployeeService;
import com.example.automapping.util.DtoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private EmployeeService employeeService;
    private DtoConvertUtil dtoConvert;

    @Autowired
    public Runner(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.dtoConvert = new DtoConvertUtil();
    }

    @Override
    public void run(String... args) throws Exception {

        simpleMapping();
    }

    private void simpleMapping() {
        Employee empl = new Employee("Georgy", "Goshev", BigDecimal.valueOf(1231.50));
        EmployeeDto emplDto = this.dtoConvert.convert(empl, EmployeeDto.class);
        System.out.println(emplDto);
    }
}
