package com.chigov.recyclerview002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chigov.recyclerview002.adapters.EmployeeAdapter;
import com.chigov.recyclerview002.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployee);
        adapter = new EmployeeAdapter();
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        employee1.setfName("Max");
        employee2.setfName("Ivan");
        employee1.setlName("Ivanov");
        employee2.setlName("Petrov");
        employee3.setfName("Max");
        employee4.setfName("Ivan");
        employee3.setlName("Ivanov");
        employee4.setlName("Petrov");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        adapter.setEmployees(employees);
    }
}