package com.shreyasmp.nbateams.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.model.TeamsList
import com.shreyasmp.nbateams.service.NBATeamService
import com.shreyasmp.nbateams.utils.ResultWrapper

interface NBATeamsRepository {

    suspend fun getNBATeamsList(): ResultWrapper<LiveData<TeamsList>>

    suspend fun getNBATeamDetails(teamName: String): ResultWrapper<LiveData<Team>>
}

class NBATeamsRepositoryImpl(private val service: NBATeamService) : NBATeamsRepository {

    companion object {
        private val TAG = NBATeamsRepositoryImpl::class.java.simpleName
    }

    override suspend fun getNBATeamsList(): ResultWrapper<LiveData<TeamsList>> {
        val nbaTeamListResults = MutableLiveData<TeamsList>()
        return try {
            val responseBody = service.fetchNBATeamListResponse()
            if (responseBody.isSuccessful && responseBody.body() != null) {
                nbaTeamListResults.postValue(responseBody.body())
                ResultWrapper.SUCCESS(nbaTeamListResults)
            } else {
                ResultWrapper.FAILURE(null)
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Exception: ${exception.message}")
            ResultWrapper.FAILURE(null)
        }
    }

    override suspend fun getNBATeamDetails(teamName: String): ResultWrapper<LiveData<Team>> {
        val nbaTeamDetailsResults = MutableLiveData<Team>()
        return try {
            val responseBody = service.fetchSelectedNBATeamDetails(teamName)
            if (responseBody.isSuccessful && responseBody.body() != null) {
                nbaTeamDetailsResults.postValue(responseBody.body())
                ResultWrapper.SUCCESS(nbaTeamDetailsResults)
            } else {
                ResultWrapper.FAILURE(null)
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Exception: ${exception.message}")
            ResultWrapper.FAILURE(null)
        }
    }
}