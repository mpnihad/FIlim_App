package com.nihad.filim_app.presenter;

import com.nihad.filim_app.interator.filimViewsInteractor;
import com.nihad.filim_app.view.view.filimViewsCallBack;

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
