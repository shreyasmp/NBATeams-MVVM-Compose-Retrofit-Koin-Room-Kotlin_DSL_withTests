package com.shreyasmp.nbateams.repository

import android.content.Context
import android.util.Log
import com.shreyasmp.nbateams.database.NBATeamsDao
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.service.NBATeamService
import com.shreyasmp.nbateams.utils.NetworkManager.isOnline
import com.shreyasmp.nbateams.utils.ResultWrapper

interface NBATeamsRepository {

    suspend fun getNBATeamsList(): ResultWrapper<List<Team>>
}

class NBATeamsRepositoryImpl(
    private val service: NBATeamService,
    private val teamDao: NBATeamsDao,
    private val context: Context
) : NBATeamsRepository {

    companion object {
        private val TAG = NBATeamsRepositoryImpl::class.java.simpleName
    }

    override suspend fun getNBATeamsList(): ResultWrapper<List<Team>> {
        // Check if app is online on any network
        if (isOnline(context)) {
            return try {
                val response = service.fetchNBATeamListResponse()
                if (response.isSuccessful && response.body() != null) {
                    // Get the response and insert into room db
                    response.body()?.teams?.let {
                        Log.d(TAG, "Inserting into DB: ")
                        teamDao.insertNBATeams(it)
                    }
                    ResultWrapper.SUCCESS(response.body()?.teams)
                } else {
                    ResultWrapper.FAILURE(null)
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Exception: ${exception.message}")
                ResultWrapper.FAILURE(null)
            }
        } else {
            // if connection is lost, try launching app, it should show db data
            val teamInDB = teamDao.queryNBATeams()
            return if (teamInDB.isNotEmpty()) {
                Log.d(TAG, "From Database: ")
                ResultWrapper.SUCCESS(teamInDB)
            } else {
                ResultWrapper.FAILURE("407")
            }
        }
    }
}