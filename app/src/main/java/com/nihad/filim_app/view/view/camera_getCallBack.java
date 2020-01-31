package com.nihad.filim_app.view.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.nihad.filim_app.model.FilimModel;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public interface camera_getCallBack {

    Context getContext();

    void storeDatas(String path);
}
