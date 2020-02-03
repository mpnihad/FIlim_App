package com.nihad.filim_app.interator;

import android.content.Context;
import android.util.Log;

import com.nihad.filim_app.Utils.APIService;
import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.model.ResponseClass;
import com.nihad.filim_app.view.view.MainActivityCallback;
import com.nihad.filim_app.view.view.camera_getCallBack;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class camera_getInteractor {

    private APIService service;

    public camera_getInteractor(camera_getCallBack callback) {

    }


    public static Map<File,String> getOutputMediaFile(int type, Context applicationContext) {

        Map<File, String> map = new HashMap<File, String>();
        File mediaStorageDir = null;

        mediaStorageDir=applicationContext.getFilesDir();

        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {



                return null;
            }
        }


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = df.format(c.getTime());

        File mediaFile;

        if (type == 1) {


            mediaFile = new File(mediaStorageDir.getPath() + File.separator + formattedDate + ".JPEG");

            Log.d("hello", "getOutputMediaFile: "+mediaFile.getPath());

        } else {
            return null;
        }

        map.put(mediaFile,mediaFile.getPath());
        return map;
    }
}