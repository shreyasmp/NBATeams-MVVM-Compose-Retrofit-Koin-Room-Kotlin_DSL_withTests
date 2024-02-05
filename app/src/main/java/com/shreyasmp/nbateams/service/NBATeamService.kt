package com.shreyasmp.nbateams.service

import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.model.TeamsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NBATeamService {

    @GET("shreyasmp/RxJavaBeginner/master/input.json")
    suspend fun fetchNBATeamListResponse(): Response<TeamsList>

    @GET("shreyasmp/RxJavaBeginner/master/details.json")
    suspend fun fetchSelectedNBATeamDetails(
        @Query("team_name") teamName: String
    ): Response<Team>
}