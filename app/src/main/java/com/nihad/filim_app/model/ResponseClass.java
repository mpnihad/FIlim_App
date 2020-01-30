package com.nihad.filim_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nihad.filim_app.model.FilimModel;

import java.util.List;

public class ResponseClass {

    @SerializedName("results")
    @Expose
    private List<FilimModel> filimModels = null;

    public List<FilimModel> getFilimModels() {
        return filimModels;
    }

    public void setFilimModels(List<FilimModel> students) {
        this.filimModels = students;
    }

}