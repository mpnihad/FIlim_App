package com.nihad.filim_app.interator;

import android.util.Log;

import com.nihad.filim_app.Utils.APIService;
import com.nihad.filim_app.database.repository.FilimModelRepository;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.model.ResponseClass;
import com.nihad.filim_app.view.view.MainActivityCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityInteractor {
    private MainActivityCallback callback;
    private APIService service;

    public MainActivityInteractor(MainActivityCallback callback) {
        this.callback = callback;
    }

//    public void getList(FilimModelRepository repository) {
//
//
//
//
//        FilimModelRepository filimModelRepository;
//
//        callback.visibleProgress(true);
//
//
//
//        service = NetworkService.getService();
//
//        Call<JsonObject> call = service.filimlist();
//
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//
//                String abc=response.body().toString();
//                if (response.isSuccessful()) {
//                    JsonObject jsonObject;
//                    ArrayList<FilimModel> filimModels=new ArrayList<>();
//                    JsonArray jsonArray = response.body().get("results").getAsJsonArray();
//
//                    for (int i=0;i<jsonArray.size();i++)
//                    {
//                        jsonObject=jsonArray.get(i).getAsJsonObject();
//                        FilimModel filimModel = new Gson().fromJson(jsonObject, FilimModel.class);
//                        FilimModelRepository filimModelsRepository =new FilimModelRepository(callback.getContext());
//                        if(repository!=null) {
//                            repository.insertTask(filimModel);
//                        }
//                        filimModels.add(filimModel);
//                        callback.visibleProgress(false);
//                    }
//
//
//
//
//
//
//
//
//                    //callback.showList(filimModels);
//                } else {
//                    callback.makeToast(response.body().get("message").toString().replaceAll("^\"|\"$", ""));
//
//                }
//                callback.visibleProgress(false);
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                callback.makeToast(call.toString());
//                callback.visibleProgress(false);
//            }
//        });
//    }

    public void getList(FilimModelRepository repository) {
        callback.visibleProgress(true);
        service = NetworkService.getClient(callback.getContext()).create(APIService.class);;
        callback.getdisposible().add(
                service.filimlist()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<ResponseClass, ResponseClass>() {
                            @Override
                            public ResponseClass apply(ResponseClass responseClass) throws Exception {
                                // TODO - note about sort

                                return responseClass;
                            }
                        })
                        .subscribeWith(new DisposableSingleObserver<ResponseClass>() {
                            @Override
                            public void onSuccess(ResponseClass responseClass) {

                                if(repository!=null) {
                                    for (FilimModel filimModel : responseClass.getFilimModels()) {

                                        repository.insertTask(filimModel);
                                    }

                                }

//                                ArrayList<FilimModel> filimModels1 = new ArrayList<>(filimModels);
                                callback.visibleProgress(false);
                            }


                            @Override
                            public void onError(Throwable e) {
                                Log.e("RxError", "onError: " + e.getMessage());
                               // showError(e);
                                callback.visibleProgress(false);
                            }
                        })
        );
    }

}