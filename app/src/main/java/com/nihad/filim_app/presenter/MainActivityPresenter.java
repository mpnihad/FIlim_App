package com.nihad.filim_app.presenter;

import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.interator.MainActivityInteractor;
import com.nihad.filim_app.view.view.MainActivityCallback;

public class MainActivityPresenter {
    private MainActivityCallback callback;
    private MainActivityInteractor interactor;

    public MainActivityPresenter(MainActivityCallback callback) {
        this.callback = callback;
    }


    public void getList(FilimModelRepository repository) {

        interactor = new MainActivityInteractor(callback);

            interactor.getList(repository);

    }


}
