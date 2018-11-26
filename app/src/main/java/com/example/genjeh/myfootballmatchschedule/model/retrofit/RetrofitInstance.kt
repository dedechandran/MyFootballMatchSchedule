package com.example.genjeh.myfootballmatchschedule.model.retrofit

import com.example.genjeh.myfootballmatchschedule.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

        private const val BASE_URL = BuildConfig.BASE_URL
        private var retrofit : Retrofit? = null
        fun newInstance() : Retrofit? {
            if(retrofit == null){
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }

}