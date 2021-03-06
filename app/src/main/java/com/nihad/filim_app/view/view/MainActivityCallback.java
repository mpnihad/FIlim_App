package com.nihad.filim_app.view.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.nihad.filim_app.model.FilimModel;

import java.util.ArrayList;
import java.util.StringJoiner;

import io.reactivex.disposables.CompositeDisposable;

public interface MainActivityCallback {
    void visibleProgress(boolean b);

    SharedPreferences getSp();

     void showList(ArrayList<FilimModel> filimModels);

    void makeToast(String message);

    void filmView(FilimModel filimModel);

    Context getContext();

    CompositeDisposable getdisposible();
}
