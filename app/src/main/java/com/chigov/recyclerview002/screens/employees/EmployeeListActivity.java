package com.chigov.recyclerview002.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chigov.recyclerview002.R;
import com.chigov.recyclerview002.adapters.EmployeeAdapter;
import com.chigov.recyclerview002.api.ApiFactory;
import com.chigov.recyclerview002.api.ApiService;
import com.chigov.recyclerview002.pojo.Employee;
import com.chigov.recyclerview002.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView{
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EmployeeListPresenter(this);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployee);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());

        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error connecting to DataBase", Toast.LENGTH_SHORT).show();
    }
}
