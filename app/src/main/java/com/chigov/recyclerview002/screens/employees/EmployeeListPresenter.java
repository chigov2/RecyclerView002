package com.chigov.recyclerview002.screens.employees;

import android.util.Log;
import android.widget.Toast;

import com.chigov.recyclerview002.api.ApiFactory;
import com.chigov.recyclerview002.api.ApiService;
import com.chigov.recyclerview002.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;
    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable(){
        if (compositeDisposable != null){compositeDisposable.dispose();}
    }
}
