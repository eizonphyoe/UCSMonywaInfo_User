package com.admin.ucsmonywa.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.UpdateTimetable;
import com.admin.ucsmonywa.fifthyear_model.SheduleItem;

import java.util.List;

public class FifthYearAdapter  extends RecyclerView.Adapter<FifthYearAdapter.FifthYearViewHolder> {
    TextView subject,duration,teacher;
    List<SheduleItem> fifthYear;
    SheduleItem model;


    public FifthYearAdapter(List<SheduleItem> fifthYear) {
        this.fifthYear = fifthYear;
    }

    @NonNull
    @Override
    public FifthYearAdapter.FifthYearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_timetable,viewGroup,false);
        return new FifthYearAdapter.FifthYearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FifthYearAdapter.FifthYearViewHolder holder, int position) {
         model = fifthYear.get(position);

        subject.setText(model.getSubject());
        duration.setText(model.getDuration());
        teacher.setText(model.getTeacher());




    }

    @Override
    public int getItemCount() {
        return fifthYear.size();
    }

    class FifthYearViewHolder extends RecyclerView.ViewHolder{


        public FifthYearViewHolder(@NonNull final View itemView) {
            super(itemView);

            subject=itemView.findViewById(R.id.txt_subject);
            duration=itemView.findViewById(R.id.txt_duration);
            teacher=itemView.findViewById(R.id.txt_teacher);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SheduleItem s= fifthYear.get(getAdapterPosition());

                    Intent intent=new Intent(view.getContext(), UpdateTimetable.class);

                    intent.putExtra("subject",s.getSubject());
                    intent.putExtra("teacher",s.getTeacher());
                    intent.putExtra("duration",s.getDuration());

                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}
