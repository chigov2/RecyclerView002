package com.chigov.recyclerview002.screens.employees;

import com.chigov.recyclerview002.pojo.Employee;

import java.util.List;

public interface EmployeeListView {
    void showData(List<Employee> employees);
    void showError();
}
