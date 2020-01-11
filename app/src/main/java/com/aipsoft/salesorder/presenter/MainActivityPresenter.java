package com.aipsoft.salesorder.presenter;

import com.aipsoft.salesorder.interator.MainActivityInteractor;
import com.aipsoft.salesorder.view.view.MainActivityCallback;

public class MainActivityPresenter {
    private MainActivityCallback callback;
    private MainActivityInteractor interactor;

    public MainActivityPresenter(MainActivityCallback callback) {
        this.callback = callback;
    }


    public void getList() {

        interactor = new MainActivityInteractor(callback);

            interactor.getList();

    }
}
