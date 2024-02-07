package com.shreyasmp.nbateams.service

import com.shreyasmp.nbateams.model.TeamsList
import retrofit2.Response
import retrofit2.http.GET

interface NBATeamService {

    @GET("shreyasmp/RxJavaBeginner/master/input.json")
    suspend fun fetchNBATeamListResponse(): Response<TeamsList>
}