package com.example.omi.du_crs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.asif.du_crs.R;
import com.example.asif.du_crs.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExamHallSchedule extends AppCompatActivity {

    List<ExamHallSlot> all_bookings=new ArrayList<>();
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Exam_Hall").child(FunctionList.exam_hall_search.hall_name);

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_hall_schedule);
        //getSupportActionBar().setTitle("Schedule");
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Exam_Hall").child(FunctionList.exam_hall_search.hall_name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                all_bookings.clear();
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    ExamHallSlot temp = new ExamHallSlot();
                    temp = users.getValue(ExamHallSlot.class);
                    if(temp.rdate.equals(ExamHallCalendar.mdate))all_bookings.add(temp);
                }
                adapter=new ExamScheduleAdapter(all_bookings);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        adapter=new ExamScheduleAdapter(all_bookings);
        recyclerView.setAdapter(adapter);

    }

}
