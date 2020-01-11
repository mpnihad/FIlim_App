package com.nihad.filim_app.view.imp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nihad.filim_app.R;
import com.nihad.filim_app.Utils.ExpandableTextView;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.presenter.filimViewsPresenter;
import com.nihad.filim_app.view.view.filimViewsCallBack;
import com.google.gson.Gson;

import java.util.ArrayList;

public class filimViews extends AppCompatActivity implements filimViewsCallBack {

    filimViewsPresenter presenter;


    TextView filim_title;
    LinearLayout filim_characters;
    TextView filim_director;
    TextView filim_episode;

    TextView filim_release_date;
    TextView filim_producer;
    TextView heading;
    LinearLayout filim_Species;
    LinearLayout filim_starships;
    LinearLayout filim_vehicles;
    LinearLayout filim_planets;
    ExpandableTextView filim_body;
    Dialog detailsDialog;
    ListView list;
    ProgressBar loading;


    ArrayList<String> species ;
    ArrayList<String> starships ;
    ArrayList<String> vehicles ;
    ArrayList<String> characters ;
    ArrayList<String> planets ;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_filim_views);

        presenter = new filimViewsPresenter(this);



        filim_title=findViewById(R.id.filim_title);
        filim_body=findViewById(R.id.filim_body);
        filim_characters=findViewById(R.id.filim_characters);
        filim_director=findViewById(R.id.filim_director);
        filim_episode=findViewById(R.id.filim_episode);
        filim_planets=findViewById(R.id.filim_planets);
        filim_producer=findViewById(R.id.filim_producer);
        filim_release_date=findViewById(R.id.filim_release_date);
        filim_Species=findViewById(R.id.filim_Species);
        filim_starships=findViewById(R.id.filim_starships);
        filim_vehicles=findViewById(R.id.filim_vehicles);






        String filim_details=getIntent().getStringExtra("film_details");
        FilimModel filimModel = new Gson().fromJson(filim_details, FilimModel.class);


        filim_title.setText(filimModel.getTitle());
        filim_body.setText(filimModel.getOpening_crawl());
        filim_director.setText(filimModel.getDirector());
        filim_episode.setText(filimModel.getEpisode_id());

        filim_release_date.setText(filimModel.getRelease_date());
        filim_producer.setText(filimModel.getProducer());

        detailsDialog = new Dialog(this);
        detailsDialog.setContentView(R.layout.detailsdialoglayout);

        Button ok = (Button) detailsDialog.findViewById(R.id.ok);
         list = (ListView) detailsDialog.findViewById(R.id.list);
        loading=(ProgressBar) detailsDialog.findViewById(R.id.progressbar);
        heading=(TextView) detailsDialog.findViewById(R.id.heading);



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsDialog.dismiss();
            }
        });
        detailsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        filim_characters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="Characters";
                heading.setText("Characters");
                if(characters==null) {
                    presenter.getList(filimModel.getCharacters());
                }
                else
                {
                    showList(characters);
                }
                detailsDialog.show();
            }
        });

        filim_planets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="Planets";
                heading.setText("Planets");
                if(planets==null) {
                    presenter.getList(filimModel.getPlanets());
                }
                else
                {

                    showList(planets);
                }
                detailsDialog.show();
            }
        });
        filim_Species.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="Species";
                heading.setText("Species");
                presenter.getList(filimModel.getSpecies());
                detailsDialog.show();
            }
        });
        filim_starships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="Star Ships";
                heading.setText("Star Ships");
                if(starships==null) {
                    presenter.getList(filimModel.getStarships());
                }
                else
                {

                    showList(starships);
                }

                detailsDialog.show();
            }
        });
        filim_vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="Vehicles";
                heading.setText("Vehicles");
                if(vehicles==null) {
                    presenter.getList(filimModel.getVehicles());
                }
                else
                {

                    showList(vehicles);
                }

                detailsDialog.show();
            }
        });


    }

    @Override
    public void visibleProgress(boolean b) {

        if(b)
        {
            loading.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }

        else {
            loading.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showList(ArrayList<String> names) {

        if(status.equals("Vehicles"))
        {vehicles=new ArrayList<>();
            vehicles.addAll(names);
        }
        else if(status.equals("Star Ships"))
        {
            starships=new ArrayList<>();
            starships.addAll(names);
        }
        else if(status.equals("Species"))
        {
            species=new ArrayList<>();
            species.addAll(names);
        }
        else if(status.equals("Planets"))
        { planets=new ArrayList<>();
            planets.addAll(names);
        }
        else if(status.equals("Characters"))
        {
            characters=new ArrayList<>();
            characters.addAll(names);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                names );

        list.setAdapter(arrayAdapter);
    }

    @Override
    public void makeToast(String message) {

    }
}
