package com.admin.ucsmonywa.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.UpdateTimetable;
import com.admin.ucsmonywa.firstyear_model.SheduleItem;

import java.util.List;

public class FirstYearAdapter  extends RecyclerView.Adapter<FirstYearAdapter.FirstYearViewHolder>  {
    TextView subject,duration,teacher;
    List<SheduleItem> firstYear;






    public FirstYearAdapter(List<SheduleItem> firstYear) {
        this.firstYear = firstYear;
    }






    @NonNull
    @Override
    public FirstYearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_timetable,viewGroup,false);
        return new FirstYearViewHolder(view);





    }

    @Override
    public void onBindViewHolder(@NonNull FirstYearViewHolder holder, int position) {
        SheduleItem model = firstYear.get(position);

        subject.setText(model.getSubject());
        duration.setText(model.getDuration());
        teacher.setText(model.getTeacher());

    }

    @Override
    public int getItemCount() {
        return firstYear.size();
    }



    class FirstYearViewHolder extends RecyclerView.ViewHolder {


        public FirstYearViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.txt_subject);
            duration = itemView.findViewById(R.id.txt_duration);
            teacher = itemView.findViewById(R.id.txt_teacher);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SheduleItem s= firstYear.get(getAdapterPosition());

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
