package com.shreyasmp.nbateams.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.service.NBATeamService
import com.shreyasmp.nbateams.utils.ResultWrapper

interface NBATeamsRepository {

    suspend fun getNBATeamsList(): ResultWrapper<LiveData<List<Team>>>
}

class NBATeamsRepositoryImpl(private val service: NBATeamService) : NBATeamsRepository {

    companion object {
        private val TAG = NBATeamsRepositoryImpl::class.java.simpleName
    }

    override suspend fun getNBATeamsList(): ResultWrapper<LiveData<List<Team>>> {
        val nbaTeamListResults = MutableLiveData<List<Team>>()
        return try {
            val responseBody = service.fetchNBATeamListResponse()
            if (responseBody.isSuccessful && responseBody.body() != null) {
                nbaTeamListResults.postValue(responseBody.body()?.teams)
                ResultWrapper.SUCCESS(nbaTeamListResults)
            } else {
                ResultWrapper.FAILURE(null)
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Exception: ${exception.message}")
            ResultWrapper.FAILURE(null)
        }
    }
}