package com.nihad.filim_app.ViewModel;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nihad.filim_app.BuildConfig;
import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.interator.MainActivityInteractor;
import com.nihad.filim_app.interator.camera_getInteractor;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.view.view.MainActivityCallback;
import com.nihad.filim_app.view.view.camera_getCallBack;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class camera_getViewModel  extends AndroidViewModel {


    private Application application;


    camera_getInteractor camera_getInteractor;
    public camera_getViewModel(@NonNull Application application) {
        super(application);
        this.application=application;


    }

    public Map<Uri,String> getOutputMediaFileUri(int type) {

        Map<Uri, String> map = new HashMap<Uri, String>();
        Map<File, String> map_return = new HashMap<File, String>();
        map_return=camera_getInteractor.getOutputMediaFile(type,application.getApplicationContext());

        File file=null;
        String path="";
        for (Map.Entry<File,String> entry : map_return.entrySet()) {
           file=entry.getKey();
           path=entry.getValue();
        }
        Uri uri= FileProvider.getUriForFile(application.getApplicationContext(),
                BuildConfig.APPLICATION_ID +
                        ".fileprovider", file// Over here
                );
        map.put(uri,path);
        return map;
    }



}
