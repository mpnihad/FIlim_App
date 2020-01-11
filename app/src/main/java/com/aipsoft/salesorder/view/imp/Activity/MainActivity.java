package com.aipsoft.salesorder.view.imp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aipsoft.salesorder.R;
import com.aipsoft.salesorder.adapter.FilimListAdapter;
import com.aipsoft.salesorder.model.FilimModel;
import com.aipsoft.salesorder.presenter.MainActivityPresenter;
import com.aipsoft.salesorder.view.view.MainActivityCallback;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        MainActivityCallback {

    RecyclerView filimList;
    ProgressBar loading;
    private MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);

        filimList=findViewById(R.id.filimlist);
        loading=findViewById(R.id.progressbar);

        presenter.getList();





    }

    @Override
    public void visibleProgress(boolean b) {
        if(b)
        {
            loading.setVisibility(View.VISIBLE);
            filimList.setVisibility(View.GONE);
        }

        else {
            loading.setVisibility(View.GONE);
            filimList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public SharedPreferences getSp() {
        return null;
    }

    @Override
    public void showList(ArrayList<FilimModel> filimModels) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        filimList.setLayoutManager(layoutManager);
        FilimListAdapter filimListAdapter = new FilimListAdapter(this,filimModels,this);
        filimList.setAdapter(filimListAdapter);

    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void filmView(FilimModel filimModel) {
        Intent intent=new Intent(this,filimViews.class);
        String filimmodel_string=new Gson().toJson(filimModel);
        intent.putExtra("film_details",filimmodel_string);
        startActivity(intent);



    }

    public class getdata extends AsyncTask {
        ArrayList<FilimModel> Filim_List=null;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {


            }
            catch ( Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(Object o) {


            super.onPostExecute(o);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }

}
