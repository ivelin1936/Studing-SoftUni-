package emplRegApp.service;

import emplRegApp.domain.entities.Employee;
import emplRegApp.domain.models.service.EmployeeServiceModel;
import emplRegApp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository emplRepo;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository emplRepo,
                               ModelMapper modelMapper) {
        this.emplRepo = emplRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeServiceModel save(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelMapper
                .map(employeeServiceModel, Employee.class);

        return this.modelMapper
                .map(this.emplRepo.save(employee), EmployeeServiceModel.class);
    }

    @Override
    public void delete(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelMapper
                .map(employeeServiceModel, Employee.class);

        this.emplRepo.delete(employee);
    }

    @Override
    public List<EmployeeServiceModel> findAll() {
        List<Employee> allEmployees = this.emplRepo.findAll();

        return allEmployees.stream()
                .map(empl -> this.modelMapper.map(empl, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeServiceModel findById(String id) {
        Employee employee = this.emplRepo.findById(id);

        return this.modelMapper
                .map(employee, EmployeeServiceModel.class);
    }
}
