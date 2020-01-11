package com.nihad.filim_app.view.view;

import java.util.ArrayList;

public interface filimViewsCallBack {
    void visibleProgress(boolean b);


    void showList(ArrayList<String> names);

    void makeToast(String message);


}
