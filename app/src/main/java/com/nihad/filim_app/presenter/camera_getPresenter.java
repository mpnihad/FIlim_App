package com.nihad.filim_app.presenter;

import android.net.Uri;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.nihad.filim_app.BuildConfig;
import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.interator.MainActivityInteractor;
import com.nihad.filim_app.interator.camera_getInteractor;
import com.nihad.filim_app.view.view.MainActivityCallback;
import com.nihad.filim_app.view.view.camera_getCallBack;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class camera_getPresenter {
    private camera_getCallBack callback;
    private camera_getInteractor interactor;

    public camera_getPresenter(camera_getCallBack callback) {
        this.callback = callback;
    }




    public Uri getOutputMediaFileUri(int type) {

        return FileProvider.getUriForFile(callback.getContext(),
                BuildConfig.APPLICATION_ID +
                        ".fileprovider", // Over here
                camera_getInteractor.getOutputMediaFile(type));
    }



}
