package com.chigov.recyclerview002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chigov.recyclerview002.adapters.EmployeeAdapter;
import com.chigov.recyclerview002.api.ApiFactory;
import com.chigov.recyclerview002.api.ApiService;
import com.chigov.recyclerview002.pojo.Employee;
import com.chigov.recyclerview002.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployee);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());

        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        apiService.getEmployees()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<EmployeeResponse>() {
            @Override
            public void accept(EmployeeResponse employeeResponse) throws Exception {
                adapter.setEmployees(employeeResponse.getResponse());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(MainActivity.this, "Error database connection"+ throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("test",throwable.getMessage());
            }
        });
    }
}

//    List<Employee> employees = new ArrayList<>();
//    Employee employee1 = new Employee();
//    Employee employee2 = new Employee();
//    Employee employee3 = new Employee();
//    Employee employee4 = new Employee();
//        employee1.setfName("Max");
//                employee2.setfName("Ivan");
//                employee1.setlName("Ivanov");
//                employee2.setlName("Petrov");
//                employee3.setfName("Max");
//                employee4.setfName("Ivan");
//                employee3.setlName("Ivanov");
//                employee4.setlName("Petrov");
//
//                employees.add(employee1);
//                employees.add(employee2);
//                employees.add(employee3);
//                employees.add(employee4);
//                adapter.setEmployees(employees);