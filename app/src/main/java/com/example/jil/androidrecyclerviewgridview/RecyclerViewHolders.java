package com.example.jil.androidrecyclerviewgridview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.myproject_prototype2sqlite.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView activityName;
    public ImageView activitypic;
    public TextView activityDesc;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        activityName = (TextView)itemView.findViewById(R.id.activity);
        activitypic = (ImageView)itemView.findViewById(R.id.userPix);
        activityDesc = (TextView)itemView.findViewById(R.id.description);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked activity Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}