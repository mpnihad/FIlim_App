package com.aipsoft.salesorder.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aipsoft.salesorder.R;
import com.aipsoft.salesorder.model.FilimModel;
import com.aipsoft.salesorder.view.view.MainActivityCallback;

import java.util.ArrayList;

public class FilimListAdapter extends RecyclerView.Adapter<FilimListAdapter.MyViewHolder> {

    LayoutInflater inflater;
    Context context;

    MainActivityCallback callback;

    ArrayList<FilimModel> filimModels = new ArrayList<>();



    public FilimListAdapter(Context context1, ArrayList<FilimModel> filimModels, MainActivityCallback callback) {

        try {
            context = context1;
            inflater = LayoutInflater.from(context1);
            this.filimModels = filimModels;
            this.callback = callback;


        } catch (Exception e) {

            Log.d("Error", e.toString());
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.film_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;



    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.title.setText(filimModels.get(position).getTitle());
        holder.releaseDate.setText(filimModels.get(position).getRelease_date());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.filmView(filimModels.get(position));

            }
        });


    }



    @Override
    public int getItemCount() {
        return filimModels.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView title;
TextView releaseDate;
LinearLayout layout;



        public MyViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            releaseDate=itemView.findViewById(R.id.releasedate);
            layout=itemView.findViewById(R.id.parentLayout);


        }


    }
}