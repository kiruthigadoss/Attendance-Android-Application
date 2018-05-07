package com.example.richa.attendance100;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Delete;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshul on 2/3/2018.
 */


public class Studentattendancelistadpter extends RecyclerView.Adapter<Studentattendancelistadpter.Myviewholder> {

    private List<StudentAttendanceModel> studentattendancelist = new ArrayList<>();
    private Context context;
    Studentattendancelistadpter studentattendancelistadapter;


    public Studentattendancelistadpter(Context context, List<StudentAttendanceModel> studentattendancelist) {
        this.context = context;
        this.studentattendancelist = studentattendancelist;
    }


    @Override
    public int getItemCount() {
        return studentattendancelist.size();
    }

    public Myviewholder onCreateViewHolder(ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.studentattendancelist, null);
        return new Myviewholder(itemview);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, final int position) {
        final StudentAttendanceModel studentAttendanceModel = studentattendancelist.get(position);
        holder.tvattendancerollno.setText(studentAttendanceModel.getRollno());
        holder.tvattendancedate.setText(studentAttendanceModel.getDate());
        holder.tvattendance.setText(studentAttendanceModel.getSpin());

        holder.deleltebtnattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do you want to delete ?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Delete().from(StudentAttendanceModel.class).where("rollno= ?", studentattendancelist.get(position).getRollno()).execute();
                                studentattendancelist.remove(position);
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert=builder.create();
                alert.setTitle("Confirmation for delete");
                alert.show();



            }
        });

    }


    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView tvattendancerollno, tvattendancedate, tvattendance;
        ImageView deleltebtnattendance;

        public Myviewholder(View view) {
            super(view);
            tvattendancerollno = (TextView) view.findViewById(R.id.studentattendancelist_tv_rollno);
            tvattendancedate = (TextView) view.findViewById(R.id.studentattendancelist_tv_date);
            tvattendance = (TextView) view.findViewById(R.id.studentattendancelist_tv_attendance);
            deleltebtnattendance = (ImageView) view.findViewById(R.id.studentattendancelist_deltebtn);

        }


    }


}