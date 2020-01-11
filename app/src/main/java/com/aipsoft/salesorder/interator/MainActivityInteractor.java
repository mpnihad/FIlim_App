package com.aipsoft.salesorder.interator;

import com.aipsoft.salesorder.Utils.APIService;
import com.aipsoft.salesorder.model.FilimModel;
import com.aipsoft.salesorder.view.view.MainActivityCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityInteractor {
    private MainActivityCallback callback;
    private APIService service;

    public MainActivityInteractor(MainActivityCallback callback) {
        this.callback = callback;
    }

    public void getList() {

        callback.visibleProgress(true);



        service = NetworkService.getService();

        Call<JsonObject> call = service.filimlist();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                String abc=response.body().toString();
                if (response.isSuccessful()) {
                    JsonObject jsonObject;
                    ArrayList<FilimModel> filimModels=new ArrayList<>();
                    JsonArray jsonArray = response.body().get("results").getAsJsonArray();

                    for (int i=0;i<jsonArray.size();i++)
                    {
                        jsonObject=jsonArray.get(i).getAsJsonObject();
                        FilimModel filimModel = new Gson().fromJson(jsonObject, FilimModel.class);
                        filimModels.add(filimModel);
                    }



                    callback.showList(filimModels);
                } else {
                    callback.makeToast(response.body().get("message").toString().replaceAll("^\"|\"$", ""));

                }
                callback.visibleProgress(false);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.makeToast(call.toString());
                callback.visibleProgress(false);
            }
        });
    }

}