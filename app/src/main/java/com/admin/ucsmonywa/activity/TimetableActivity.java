package com.admin.ucsmonywa.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.fragment.FridayFragment;
import com.admin.ucsmonywa.fragment.MondayFragment;
import com.admin.ucsmonywa.fragment.ThursdayFragment;
import com.admin.ucsmonywa.fragment.TuesdayFragment;
import com.admin.ucsmonywa.fragment.WednesdayFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TimetableActivity extends AppCompatActivity {

    Button monBtn, tueBtn, wedBtn, thuBtn, friBtn;
    FloatingActionButton addfloatBtn;
    Fragment fragment = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_layout);

        monBtn = findViewById(R.id.monday_btn);
        tueBtn = findViewById(R.id.tuesday_btn);
        wedBtn = findViewById(R.id.wednesday_btn);
        thuBtn = findViewById(R.id.thursday_btn);
        friBtn = findViewById(R.id.friday_btn);


    /*   addfloatBtn=findViewById(R.id.add_float);

        addfloatBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(TimetableActivity.this,AddTimetable.class);
               startActivity(intent);
           }
    });*/
        Fragment fra = new MondayFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fra);
        fragmentTransaction.commit();


        //for monday
        monBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment = new MondayFragment();
                Log.d("t", "Fail");

                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                    Log.d("e", "Successful");
                }
            }
        });

        //for tueday
        tueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment = new TuesdayFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                    Log.d("e", "Successful");
                }
            }
        });

        //for wednesday
        wedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment = new WednesdayFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                    Log.d("e", "Successful");
                }

            }
        });

        //for thursday
        thuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment = new ThursdayFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                    Log.d("e", "Successful");
                }


            }
        });

        //for friday
        friBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragment = new FridayFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();


                    Log.d("e", "Successful");
                }


            }
        });
    }



    }


