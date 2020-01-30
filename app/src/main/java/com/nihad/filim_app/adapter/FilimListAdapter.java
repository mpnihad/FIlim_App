package com.nihad.filim_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nihad.filim_app.R;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.view.view.MainActivityCallback;

import java.util.ArrayList;

public class FilimListAdapter extends ListAdapter<FilimModel,FilimListAdapter.MyViewHolder> {



    MainActivityCallback callback;



//    public FilimListAdapter(Context context1, ArrayList<FilimModel> filimModels, MainActivityCallback callback) {
//
//        try {
//            context = context1;
//            inflater = LayoutInflater.from(context1);
//            this.filimModels = filimModels;
//            this.callback = callback;
//
//
//        } catch (Exception e) {
//
//            Log.d("Error", e.toString());
//        }
//    }


    private static final DiffUtil.ItemCallback<FilimModel>DIFF_CALLBACK = new DiffUtil.ItemCallback<FilimModel>() {
        @Override
        public boolean areItemsTheSame(FilimModel oldItem,FilimModel newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(FilimModel oldItem,FilimModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    public FilimListAdapter(MainActivityCallback callback) {
        super(DIFF_CALLBACK);
        this.callback=callback;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.film_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;



    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.title.setText(getItem(position).getTitle());
        holder.releaseDate.setText(getItem(position).getRelease_date());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.filmView(getItem(position));

            }
        });


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