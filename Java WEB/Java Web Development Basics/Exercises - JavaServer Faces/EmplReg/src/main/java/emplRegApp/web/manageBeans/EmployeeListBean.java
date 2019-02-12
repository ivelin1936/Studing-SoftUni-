package emplRegApp.web.manageBeans;

import emplRegApp.domain.models.view.EmployeeViewModel;
import emplRegApp.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<EmployeeViewModel> employees;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService,
                            ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = getAllEmployees();
    }

    public List<EmployeeViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeViewModel> employees) {
        this.employees = employees;
    }

    public BigDecimal getTotalMoney() {
        return this.employees
                .stream()
                .map(EmployeeViewModel::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAverageSalary() {
        return this.employees.size() == 0 ? BigDecimal.ZERO
                : this.getTotalMoney().divide(new BigDecimal(this.employees.size()), RoundingMode.HALF_UP);
    }

    private List<EmployeeViewModel> getAllEmployees() {
        return this.employeeService.findAll()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }
}
