package com.example.richa.attendance100;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshul on 12/29/2017.
 */

public class Studentlistadapter extends RecyclerView.Adapter<Studentlistadapter.Myviewholder> {

    private List<StudentModel> studentlist = new ArrayList<>();
    private Context context;
    Studentlistadapter studentlistadapter;


    public Studentlistadapter(Context context, List<StudentModel> studentlist) {
        this.context = context;
        this.studentlist = studentlist;
    }

    @Override
    public int getItemCount() {
        return studentlist.size();
    }

    public Myviewholder onCreateViewHolder(ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.studentlistrow, null);
        return new Myviewholder(itemview);
    }

    @Override
    public void onBindViewHolder(final Myviewholder holder, final int position)

    {
        final StudentModel studentModel = studentlist.get(position);
        holder.tvrollno.setText(studentModel.getRollno());
        holder.tvname.setText(studentModel.getName());
        holder.tvphoneno.setText(studentModel.getPhoneno());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do you want to delete this ?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Delete().from(StudentModel.class).where("rollno= ?", studentlist.get(position).getRollno()).execute();
                                studentlist.remove(position);
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Select().from(StudentModel.class).where("rollno= ?", studentlist.get(position).getRollno()).execute();
                Intent intent = new Intent(view.getContext(), StudentAttendance.class);
                view.getContext().startActivity(intent);
                SharedPreferences pref = view.getContext().getSharedPreferences("mypref", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("rollnoo", holder.tvrollno.getText().toString());
                editor.commit();



               }
        });
    }


    public class Myviewholder extends RecyclerView.ViewHolder {

        TextView tvrollno, tvname, tvphoneno;
        ImageView delete, displaydetail;
        CardView cardView;

        public Myviewholder(View view) {
            super(view);
            delete = (ImageView) view.findViewById(R.id.studentlist_deletebtn);

            tvrollno = (TextView) view.findViewById(R.id.studentlist_rollno_tv);
            tvname = (TextView) view.findViewById(R.id.studentlist_name_tv);
            tvphoneno = (TextView) view.findViewById(R.id.studentlist_phoneno_tv);
            cardView = (CardView) view.findViewById(R.id.cardview);


        }


    }


}