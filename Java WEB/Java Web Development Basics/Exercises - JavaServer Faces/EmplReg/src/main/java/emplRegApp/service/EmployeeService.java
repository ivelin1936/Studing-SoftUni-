package emplRegApp.service;

import emplRegApp.domain.models.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {

    EmployeeServiceModel save(EmployeeServiceModel employeeServiceModel);

    void remove(String id);

    List<EmployeeServiceModel> findAll();

    EmployeeServiceModel findById(String id);
}
