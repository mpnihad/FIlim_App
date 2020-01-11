package com.aipsoft.salesorder.presenter;

import com.aipsoft.salesorder.interator.filimViewsInteractor;
import com.aipsoft.salesorder.view.view.filimViewsCallBack;

public class filimViewsPresenter {
    private filimViewsCallBack callback;
    private filimViewsInteractor interactor;

    public filimViewsPresenter(filimViewsCallBack callback) {
        this.callback = callback;
    }


    public void getList(String[] characters) {

        interactor = new filimViewsInteractor(callback);

        interactor.getList(characters);

    }
}
