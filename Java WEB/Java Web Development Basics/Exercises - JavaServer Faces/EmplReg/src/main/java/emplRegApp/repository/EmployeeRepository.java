package emplRegApp.repository;

import emplRegApp.domain.entities.Employee;

public interface EmployeeRepository extends GenericRepository<Employee, String> {

    void delete(String id);
}
