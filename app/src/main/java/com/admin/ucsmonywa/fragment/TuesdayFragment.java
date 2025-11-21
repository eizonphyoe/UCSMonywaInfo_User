package com.admin.ucsmonywa.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.adapter.FifthYearAdapter;
import com.admin.ucsmonywa.adapter.FirstYearAdapter;
import com.admin.ucsmonywa.adapter.FourthYearAdapter;
import com.admin.ucsmonywa.adapter.SecondYearAdapter;
import com.admin.ucsmonywa.adapter.ThirdYearAdapter;
import com.admin.ucsmonywa.api.ApiInterface;
import com.admin.ucsmonywa.fifthyear_model.FifthYearData;
import com.admin.ucsmonywa.firstyear_model.FirstYearData;
import com.admin.ucsmonywa.fourthyear_model.FourthYearData;
import com.admin.ucsmonywa.secondyear_model.SecondYearData;
import com.admin.ucsmonywa.thirdyear_model.ThirdYearData;
import com.admin.ucsmonywa.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TuesdayFragment  extends Fragment {
    ApiInterface apiInterface;

    private List<FirstYearData> firstYearDataList = new ArrayList<>();
    private List<SecondYearData> secondYearDataList=new ArrayList<>();
    private List<ThirdYearData> thirdYearDataList=new ArrayList<>();
    private List<FourthYearData> fourthYearDataList=new ArrayList<>();
    private List<FifthYearData> fifthYearDataList=new ArrayList<>();

    FirstYearAdapter firstYearAdapter;
    SecondYearAdapter secondYearAdapter;
    ThirdYearAdapter thirdYearAdapter;
    FourthYearAdapter fourthYearAdapter;
    FifthYearAdapter fifthYearAdapter;

    RecyclerView recyclerView;


    Call<FirstYearData> firstCall;
    Call<SecondYearData> secondCall;
    Call<ThirdYearData> thirdCall;
    Call<FourthYearData> fourthCall;
    Call<FifthYearData> fifthCall;



    String year;
    String section;
    private SharedPreferences sharedPreferences;

    //Name of shared preferences file
    private String sharedPrefFile = "com.internship.ucsmonywa";

    public static String YEAR = "YEAR";
    public static String SECTION = "SECTION";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        apiInterface = ApiUtils.getApi();
        sharedPreferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        year = sharedPreferences.getString(YEAR,"");
        section = sharedPreferences.getString(SECTION, "");

        Log.d("Shared", year+"");
        Log.d("Shared", section+"");

        if(year.equals("First Year")){
            loadDataFirstYear();
        }else if (year.equals("Second Year")){
            loadDataSecondYear();
        }else if (year.equals("Third Year")){
            loadDataThirdYear();
        }else if (year.equals("Fourth Year")){
            loadDataFourthYear();
        }else if (year.equals("Fifth Year")){
            loadDataFifthYear();
        }



        return view;
    }


    public void loadDataFirstYear() {

        if (year.equals("First Year" )&& section.equals("Section A") ){

            firstCall= apiInterface.getFirstYearA();

        } else if (year.equals("First Year" )&& section.equals("Section B")){

            firstCall = apiInterface.getFirstYearB();

        }else if(year.equals("First Year") && section.equals("Section C")){

            firstCall=apiInterface.getFirstYearC();

        }else if (year.equals("First Year") && section.equals("Section D")){

            firstCall=apiInterface.getFirstYearD();

        }else if(year.equals("First Year") && section.equals("Section E")){

            firstCall=apiInterface.getFirstYearE();
        }


        firstCall.enqueue(new Callback<FirstYearData>() {
            @Override
            public void onResponse(Call<FirstYearData> call, Response<FirstYearData> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getTuesday() != null) {
                    if (!firstYearDataList.isEmpty()) {
                        firstYearDataList.clear();
                    }

                    firstYearAdapter = new FirstYearAdapter(response.body().getTuesday().getShedule());
                    recyclerView.setAdapter(firstYearAdapter);


                } else {
                    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<FirstYearData> call, Throwable t) {
                Toast.makeText(getContext(), "Fail!"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });




    }

    public void loadDataSecondYear(){


        if (year.equals("Second Year" )&& section.equals("Section A") ){

            secondCall= apiInterface.getSecondYearA();

        } else if (year.equals("Second Year" )&& section.equals("Section B")){

            secondCall = apiInterface.getSecondYearB();

        }else if(year.equals("Second Year") && section.equals("Section C")){

            secondCall=apiInterface.getSecondYearC();

        }else if (year.equals("Second Year") && section.equals("Section D")){

            secondCall=apiInterface.getSecondYearD();

        }else if(year.equals("Second Year") && section.equals("CT")){

            secondCall=apiInterface.getSecondYearCT();
        }


        secondCall.enqueue(new Callback<SecondYearData>() {
            @Override
            public void onResponse(Call<SecondYearData> call, Response<SecondYearData> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getTuesday() != null) {
                    if (!secondYearDataList.isEmpty()) {
                        secondYearDataList.clear();
                    }

                    secondYearAdapter = new SecondYearAdapter(response.body().getTuesday().getShedule());
                    recyclerView.setAdapter(secondYearAdapter);


                } else {
                    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<SecondYearData> call, Throwable t) {
                Toast.makeText(getContext(), "Fail!"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });




    }

    public void loadDataThirdYear(){

        if (year.equals("Third Year" )&& section.equals("Section A") ){

            thirdCall= apiInterface.getThirdYearA();

        } else if (year.equals("Third Year" )&& section.equals("Section B")){

            thirdCall = apiInterface.getThirdYearB();

        }else if(year.equals("Third Year") && section.equals("Section C")){

            thirdCall=apiInterface.getThirdYearC();

        }else if (year.equals("Third Year") && section.equals("Section D")){

            thirdCall=apiInterface.getThirdYearD();

        }else if(year.equals("Third Year") && section.equals("CT")){

            thirdCall=apiInterface.getThirdYearCT();
        }


        thirdCall.enqueue(new Callback<ThirdYearData>() {
            @Override
            public void onResponse(Call<ThirdYearData> call, Response<ThirdYearData> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getTuesday() != null) {
                    if (!thirdYearDataList.isEmpty()) {
                        thirdYearDataList.clear();
                    }

                    thirdYearAdapter = new ThirdYearAdapter(response.body().getTuesday().getShedule());
                    recyclerView.setAdapter(thirdYearAdapter);


                } else {
                    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<ThirdYearData> call, Throwable t) {
                Toast.makeText(getContext(), "Fail!"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });



    }

    public void loadDataFourthYear(){

        if (year.equals("Fourth Year" )&& section.equals("Section A") ){

        }else if(year.equals("Fourth Year") && section.equals("")){
            fourthCall= apiInterface.getFourthYearA();

        } else if (year.equals("Fourth Year" )&& section.equals("Section B")){

            fourthCall = apiInterface.getFourthYearB();

        }else if(year.equals("Fourth Year") && section.equals("Section C")){

            fourthCall=apiInterface.getFourthYearC();

        }else if (year.equals("Fourth Year") && section.equals("Section D")){

            fourthCall=apiInterface.getFourthYearD();

        }else if(year.equals("Fourth Year") && section.equals("CT")){

            fourthCall=apiInterface.getFourthYearCT();
        }


        fourthCall.enqueue(new Callback<FourthYearData>() {
            @Override
            public void onResponse(Call<FourthYearData> call, Response<FourthYearData> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getTuesday() != null) {
                    if (!fourthYearDataList.isEmpty()) {
                        fourthYearDataList.clear();
                    }

                    fourthYearAdapter = new FourthYearAdapter(response.body().getTuesday().getShedule());
                    recyclerView.setAdapter(fourthYearAdapter);


                } else {
                    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<FourthYearData> call, Throwable t) {
                Toast.makeText(getContext(), "Fail!"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }

    public void loadDataFifthYear(){
        if (year.equals("Fifth Year" )&& section.equals("Section A") ){

            fifthCall= apiInterface.getFifthYearA();

        } else if (year.equals("Fifth Year" )&& section.equals("Section B")){

            fifthCall = apiInterface.getFifthYearB();

        }else if(year.equals("Fifth Year") && section.equals("Section C")){

            fifthCall=apiInterface.getFifthYearC();

        }else if (year.equals("Fifth Year") && section.equals("Section D")){

            fifthCall=apiInterface.getFifthYearD();

        }else if(year.equals("Fifth Year") && section.equals("CT")){

            fifthCall=apiInterface.getFifthYearCT();
        }


        fifthCall.enqueue(new Callback<FifthYearData>() {
            @Override
            public void onResponse(Call<FifthYearData> call, Response<FifthYearData> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getTuesday() != null) {
                    if (!fifthYearDataList.isEmpty()) {
                        fifthYearDataList.clear();
                    }

                    fifthYearAdapter = new FifthYearAdapter(response.body().getTuesday().getShedule());
                    recyclerView.setAdapter(fifthYearAdapter);


                } else {
                    Toast.makeText(getContext(), "No timetable data available", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<FifthYearData> call, Throwable t) {
                Toast.makeText(getContext(), "Fail!"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
