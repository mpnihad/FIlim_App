package com.nihad.filim_app.view.imp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nihad.filim_app.R;
import com.nihad.filim_app.ViewModel.FilimViewModel;
import com.nihad.filim_app.adapter.FilimListAdapter;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.presenter.MainActivityPresenter;
import com.nihad.filim_app.view.view.MainActivityCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements
        MainActivityCallback {



    RecyclerView filimList;
    ProgressBar loading;
    private MainActivityPresenter presenter;
    private CompositeDisposable disposable ;
    private FilimListAdapter mAdapter;
    FilimViewModel filimViewModel;
    List<FilimModel> filimModel;
    Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(2);

        mTopToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.menu_main_record)
                {
                    Intent intent=new Intent(MainActivity.this,RecordPage.class);
                    disposable.dispose();
                    startActivity(intent);
                }

                return false;
            }
        });

        presenter = new MainActivityPresenter(this);
        disposable = new CompositeDisposable();
        filimList=findViewById(R.id.filimlist);
        loading=findViewById(R.id.progressbar);
        filimModel=new ArrayList<>();

        filimViewModel = ViewModelProviders.of(this).get(FilimViewModel.class);
//        mAdapter = new FilimListAdapter(this);

        ArrayList<FilimModel> filimModels=new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                filimList.setLayoutManager(layoutManager);
        mAdapter = new FilimListAdapter(this);
                filimList.setAdapter(mAdapter);

//        filimViewModel.getAllFilim().observe(this, filimModel -> {
//            Log.d("called", filimModels.toString());
//            mAdapter.show_data(filimModels); } );

        filimViewModel.getAllFilim().observe(this, new Observer<List<FilimModel>>() {
            @Override
            public void onChanged(@Nullable List<FilimModel> filimModels) {

//                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                filimList.setLayoutManager(layoutManager);
//                FilimListAdapter filimListAdapter = new FilimListAdapter(MainActivity.this, (ArrayList<FilimModel>) filimModels,MainActivity.this);
//                filimList.setAdapter(filimListAdapter);


                mAdapter.submitList((ArrayList<FilimModel>) filimModels);


            }
        });



        filimViewModel.getcount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer count) {

//                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                filimList.setLayoutManager(layoutManager);
//                FilimListAdapter filimListAdapter = new FilimListAdapter(MainActivity.this, (ArrayList<FilimModel>) filimModels,MainActivity.this);
//                filimList.setAdapter(filimListAdapter);


                if(count!=0)
                {
                    visibleProgress(false);
                }


            }
        });
        presenter.getList(filimViewModel.getRepository());








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        filimList.setLayoutManager(layoutManager);
//        FilimListAdapter filimListAdapter = new FilimListAdapter(this);
//        filimList.setAdapter(filimListAdapter);

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

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public CompositeDisposable getdisposible() {
        return disposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
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

