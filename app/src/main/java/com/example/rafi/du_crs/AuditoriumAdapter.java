package com.example.rafi.du_crs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asif.du_crs.R;
import com.example.omi.du_crs.AuditorioumDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumAdapter extends RecyclerView.Adapter<AuditoriumAdapter.sViewHolder> {
    private List<AuditorioumDetails> list;
    private Context context;
    private List<String> mp = new ArrayList<>();

    public AuditoriumAdapter(Context con, List<AuditorioumDetails> list) {
        this.list = list;
        context = con;
    }

    @Override
    public AuditoriumAdapter.sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.examcard, parent, false);
        return new AuditoriumAdapter.sViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AuditoriumAdapter.sViewHolder holder, int position) {

        final AuditorioumDetails temp = list.get(position);

        //node tt=node.stringtoclass(temp.getRdate());
        holder.text1.setText("" + temp.getRdate().split("/")[0]);
        String month = temp.getRdate().split("/")[1];
        if (month.equals("1")) {
            holder.text2.setText("January");
        } else if (month.equals("2")) {
            holder.text2.setText("February");
        } else if (month.equals("3")) {
            holder.text2.setText("March");
        } else if (month.equals("3")) {
            holder.text2.setText("April");
        } else if (month.equals("5")) {
            holder.text2.setText("May");
        } else if (month.equals("6")) {
            holder.text2.setText("June");
        } else if (month.equals("7")) {
            holder.text2.setText("July");
        } else if (month.equals("8")) {
            holder.text2.setText("August");
        } else if (month.equals("9")) {
            holder.text2.setText("September");
        } else if (month.equals("10")) {
            holder.text2.setText("October");
        } else if (month.equals("11")) {
            holder.text2.setText("November");
        } else if (month.equals("12")) {
            holder.text2.setText("December");
        }


        holder.text3.setText(standardtime(temp.getStartt()) + " - " + standardtime(temp.getEndt()));
        holder.text4.setText(temp.getVenue());
        if (temp.getStatus() == 0)
            holder.status.setText("Pending");
        else if (temp.getStatus() == 1)
            holder.status.setText("Accepted");
        else
            holder.status.setText("Canceled");

        //holder.setIsRecyclable(false);
        /*holder.search_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Exam_Hall").child(FunctionList.exam_hall_search.hall_name).child(temp.getReservetionId());
                //databaseReference.removeValue();

            }
        });*/
        holder.cancel_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_buider = new AlertDialog.Builder(context);

                a_buider.setMessage("Do you want to cancel this reservation?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Exam_Hall").child(FunctionList.exam_hall_search.hall_name).child(temp.getReservetionId());
                                // databaseReference.removeValue();

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Auditorioum bookings")
                                        .child(temp.getReservetionid());
                                databaseReference.removeValue();
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Exam_Hall").child(FunctionList.exam_hall_search.hall_name).child(temp.getReservetionId());
                //databaseReference.removeValue();
                AlertDialog alert = a_buider.create();
                alert.setTitle("Confirmation");
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class sViewHolder extends RecyclerView.ViewHolder {
        public TextView text1, text2, text3, text4, status;
        public Button cancel_b;
        public CardView cv;

        public sViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.datee);
            text2 = itemView.findViewById(R.id.monthe);
            text3 = itemView.findViewById(R.id.slote);
            text4 = itemView.findViewById(R.id.sube);
            status = itemView.findViewById(R.id.status);
            cancel_b = itemView.findViewById(R.id.cancelb);
            status.setVisibility(View.VISIBLE);
            cv = itemView.findViewById(R.id.cv);

        }


    }

    static String standardtime(int ms) {
        String s = "";
        String isPm = "AM";
        if (ms > 12 * 60) isPm = "PM";
        ms = (ms % (12 * 60));
        int sh = ms / 60;
        int sm = ms % 60;
        if (sh == 0) sh = 12;

        s = sh + ":" + sm + " " + isPm;
        s = "";
        if (sh < 10) s += "0" + sh + ":";
        else s += sh + ":";
        if (sm < 10) s += "0" + sm;
        else s += sm;
        s += " " + isPm;
        return s;
    }

}