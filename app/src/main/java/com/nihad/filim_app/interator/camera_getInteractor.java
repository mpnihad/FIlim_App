package com.nihad.filim_app.interator;

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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class camera_getInteractor {
    private static camera_getCallBack callback;
    private APIService service;

    public camera_getInteractor(camera_getCallBack callback) {
        this.callback = callback;
    }


    public static File getOutputMediaFile(int type) {


        File mediaStorageDir = null;

        mediaStorageDir=callback.getContext().getFilesDir();

        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {

                Log.e("Item Attachment",
                        "Failed to create directory MyCameraVideo.");

                return null;
            }
        }


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = df.format(c.getTime());

        File mediaFile;

        if (type == 1) {


            mediaFile = new File(mediaStorageDir.getPath() + File.separator + formattedDate + ".jpg");
            callback.storeDatas(mediaFile.getPath());
            Log.d("hello", "getOutputMediaFile: "+mediaFile.getPath());

        } else {
            return null;
        }

        return mediaFile;
    }
}