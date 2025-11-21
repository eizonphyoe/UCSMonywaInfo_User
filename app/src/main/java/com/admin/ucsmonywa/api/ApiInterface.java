package com.admin.ucsmonywa.api;

import com.admin.ucsmonywa.fifthyear_model.FifthYearData;
import com.admin.ucsmonywa.firstyear_model.FirstYearData;
import com.admin.ucsmonywa.fourthyear_model.FourthYearData;
import com.admin.ucsmonywa.secondyear_model.SecondYearData;
import com.admin.ucsmonywa.thirdyear_model.ThirdYearData;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //for first year
    @GET("firstyear/A")
    Call<FirstYearData> getFirstYearA();

    @GET("firstyear/B")
    Call<FirstYearData> getFirstYearB();

    @GET("firstyear/C")
    Call<FirstYearData> getFirstYearC();

    @GET("firstyear/D")
    Call<FirstYearData> getFirstYearD();

    @GET("firstyear/E")
    Call<FirstYearData> getFirstYearE();

    //for second year

    @GET("secondyear/A")
    Call<SecondYearData> getSecondYearA();

    @GET("secondyear/B")
    Call<SecondYearData> getSecondYearB();

    @GET("secondyear/C")
    Call<SecondYearData> getSecondYearC();

    @GET("secondyear/D")
    Call<SecondYearData> getSecondYearD();

    @GET("secondyear/CT")
    Call<SecondYearData> getSecondYearCT();

    //for third year

    @GET("thirdyear/A")
    Call<ThirdYearData> getThirdYearA();

    @GET("thirdyear/B")
    Call<ThirdYearData> getThirdYearB();

    @GET("thirdyear/C")
    Call<ThirdYearData> getThirdYearC();

    @GET("thirdyear/D")
    Call<ThirdYearData> getThirdYearD();

    @GET("thirdyear/CT")
    Call<ThirdYearData> getThirdYearCT();

    //for fourth year

    @GET("fourthyear/A")
    Call<FourthYearData> getFourthYearA();

    @GET("fourthyear/B")
    Call<FourthYearData> getFourthYearB();

    @GET("fourthyear/C")
    Call<FourthYearData> getFourthYearC();

    @GET("fourthyear/D")
    Call<FourthYearData> getFourthYearD();

    @GET("fourthyear/CT")
    Call<FourthYearData> getFourthYearCT();

    //for fifth year
    @GET("fifthyear/A")
    Call<FifthYearData> getFifthYearA();

    @GET("fifthyear/B")
    Call<FifthYearData> getFifthYearB();

    @GET("fifthyear/C")
    Call<FifthYearData> getFifthYearC();

    @GET("fifthyear/D")
    Call<FifthYearData> getFifthYearD();

    @GET("fifthyear/CT")
    Call<FifthYearData> getFifthYearCT();

}