package com.shreyasmp.nbateams.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.repository.NBATeamsRepository
import com.shreyasmp.nbateams.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.VisibleForTesting

class NBATeamsViewModel(private val repository: NBATeamsRepository) : ViewModel() {

    private val _nbaTeamsListResponse: MutableLiveData<List<Team>?> = MutableLiveData()
    val nbaTeamsListResponse: LiveData<List<Team>?> = _nbaTeamsListResponse

    private val isError: MutableState<Boolean> = mutableStateOf(false)
    var isLoading: MutableState<Boolean> = mutableStateOf(false)

    init {
        fetchNBATeamsList()
    }

    @VisibleForTesting
    internal fun fetchNBATeamsList() {
        isLoading.value = true
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getNBATeamsList()
            }
            when (result) {
                is ResultWrapper.SUCCESS -> {
                    isError.value = false
                    _nbaTeamsListResponse.value = result.value.value
                }

                is ResultWrapper.FAILURE -> {
                    isError.value = true
                    _nbaTeamsListResponse.value = null
                }
            }
            isLoading.value = false
        }
    }
}