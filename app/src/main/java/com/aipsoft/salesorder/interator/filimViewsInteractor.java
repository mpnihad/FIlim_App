package com.aipsoft.salesorder.interator;

import com.aipsoft.salesorder.BuildConfig;
import com.aipsoft.salesorder.Utils.APIService;
import com.aipsoft.salesorder.model.FilimModel;
import com.aipsoft.salesorder.view.view.filimViewsCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class filimViewsInteractor {
    private filimViewsCallBack callback;
    private APIService service;

    public filimViewsInteractor(filimViewsCallBack callback) {
        this.callback = callback;
    }

    public void getList(String[] characters) {





        service = NetworkService.getService();

        ArrayList<String> names=new ArrayList<>();
        for (int i=0;i<characters.length;i++) {
            callback.visibleProgress(true);


            Call<JsonObject> call = service.getlistdatas("api/"+characters[i].replace(BuildConfig.BASE_URL,""));


            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                    String abc = response.body().toString();
                    if (response.isSuccessful()) {
                        JsonObject jsonObject;
                        ArrayList<FilimModel> filimModels = new ArrayList<>();
                        String name = response.body().get("name").getAsString();

                        names.add(name);


                        if(names.size()==characters.length) {
                            callback.showList(names);
                            callback.visibleProgress(false);
                        }
                    } else {
                        callback.makeToast(response.body().get("message").toString().replaceAll("^\"|\"$", ""));

                    }


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    callback.makeToast(call.toString());
                    callback.visibleProgress(false);
                }
            });
        }
    }

}