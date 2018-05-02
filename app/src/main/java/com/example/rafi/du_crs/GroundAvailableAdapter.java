package com.example.rafi.du_crs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asif.du_crs.Classroom_booked_Adapter;
import com.example.asif.du_crs.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafi on 01-May-18.
 */

public class GroundAvailableAdapter extends RecyclerView.Adapter<GroundAvailableAdapter.MyViewHolder> {
    List<Ground_object> gList = new ArrayList<>();
    Context context;

    DatabaseReference databaseReference;

    public GroundAvailableAdapter(List<Ground_object> gList, Context context) {
        this.gList = gList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ground_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ground_object groundObject = gList.get(position);
        String detail;
        if(groundObject.isShowTime()){
            detail = groundObject.getStartTime() + " - " +groundObject.getEndTime();
        }
        else {
            detail = groundObject.getDate();
        }
        holder.groundName.setText(groundObject.groundName);
        holder.groundDetail.setText(detail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(/*class of booking page*/);
                //send class
                // intent
            }
        });
    }

    @Override
    public int getItemCount() {
        return gList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView groundName;
        TextView groundDetail;

        public MyViewHolder(View itemView) {
            super(itemView);

            groundName = itemView.findViewById(R.id.tv_ground_name);
            groundDetail = itemView.findViewById(R.id.tv_ground_detail);
        }




    }

}
