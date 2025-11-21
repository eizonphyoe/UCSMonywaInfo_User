package com.admin.ucsmonywa.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.Activities;
import com.admin.ucsmonywa.adapter.ActivityAdapter;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.model.ActivityInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ActivityAdapter adapter;

    ProgressDialog progressDialog;

    FloatingActionButton addNotice;

    List<ActivityInfo> activityInfoList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.noticeboard_fragment, container, false);

        recyclerView=view.findViewById(R.id.recyler);

        addNotice=view.findViewById(R.id.fab);

        addNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Activities.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressDialog=new ProgressDialog(getContext());

        progressDialog.setMessage("Loading....");

        progressDialog.show();
        databaseReference= FirebaseDatabase.getInstance().getReference(AppConstants.Firebase.ACTIVITIES_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                activityInfoList.clear();
                for(DataSnapshot postSnapShot :dataSnapshot.getChildren()){
                    ActivityInfo activityInfo=postSnapShot.getValue(ActivityInfo.class);
                    if (activityInfo != null) {
                        activityInfoList.add(activityInfo);
                    }
                }

                adapter=new ActivityAdapter(activityInfoList,getContext());
                recyclerView.setAdapter(adapter);
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
        return view;
    }
}
