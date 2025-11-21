package com.admin.ucsmonywa.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.admin.ucsmonywa.R;



public class UpdateTimetable extends AppCompatActivity {
    EditText subject,teacher,duration;
    Button saveBtn;

    //URL to get JSON
    private static String url = "http://192.168.100.72:3000/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_timetable);

        subject=findViewById(R.id.edit_text_subject);
        teacher=findViewById(R.id.edit_text_teacher);
        duration=findViewById(R.id.edit_text_duration);

        saveBtn=findViewById(R.id.save_btn);


        Intent intent = this.getIntent();
        final Bundle bundle = intent != null ? intent.getExtras() : null;

        if (bundle != null) {
            String subjectText = (String) bundle.getSerializable("subject");
            String teacherText = (String) bundle.getSerializable("teacher");
            String durationText = (String) bundle.getSerializable("duration");

            if (subjectText != null) subject.setText(subjectText);
            if (teacherText != null) teacher.setText(teacherText);
            if (durationText != null) duration.setText(durationText);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTimetable(bundle);

                Log.d("aa","Click");
            }
        });

    }

    public  void updateTimetable(Bundle bundle) {

        String s = subject.getText().toString().trim();
        String t = teacher.getText().toString().trim();
        String d = duration.getText().toString().trim();

        if (s.isEmpty()) {
            subject.setError("Subject Required");
            subject.requestFocus();
            return;
        }
        if (t.isEmpty()) {
            teacher.setError("Teacher Required");
            teacher.requestFocus();
            return;
        }
        if (d.isEmpty()) {
            duration.setError("Duration Required");
            duration.requestFocus();
            return;
        }


    }


}
