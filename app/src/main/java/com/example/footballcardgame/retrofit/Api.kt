package com.example.footballcardgame.retrofit

import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.data.models.PlayerDetail
import retrofit2.Call
import retrofit2.http.GET

interface Api {

//    @Mock(Constants.MOCK_JSON_FILE_NAME)
    @GET("playerDetails")
    fun getListOfPlayerDetails(): Call<List<PlayerDetail>>
}