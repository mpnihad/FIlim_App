package com.aipsoft.salesorder.view.view;

import android.content.SharedPreferences;

import com.aipsoft.salesorder.model.FilimModel;

import java.util.ArrayList;

public interface MainActivityCallback {
    void visibleProgress(boolean b);

    SharedPreferences getSp();

     void showList(ArrayList<FilimModel> filimModels);

    void makeToast(String message);

    void filmView(FilimModel filimModel);
}
