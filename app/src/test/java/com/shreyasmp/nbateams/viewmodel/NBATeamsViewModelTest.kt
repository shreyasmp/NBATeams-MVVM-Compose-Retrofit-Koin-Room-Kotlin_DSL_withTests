package com.shreyasmp.nbateams.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.shreyas.nytimes.utils.TestJsonUtils
import com.shreyasmp.nbateams.model.Team
import com.shreyasmp.nbateams.model.TeamsList
import com.shreyasmp.nbateams.repository.NBATeamsRepositoryImpl
import com.shreyasmp.nbateams.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NBATeamsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var mockRepository: NBATeamsRepositoryImpl

    private lateinit var viewModel: NBATeamsViewModel

    @Mock
    private lateinit var mockObserver: Observer<TeamsList?>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = NBATeamsViewModel(mockRepository)
    }

    @Test
    fun `view model is not null`() {
        assertThat(viewModel).isNotNull()
    }

    @Test
    fun `on http success fetch nba teams response results are expected`() {
        runTest {
            val response = TestJsonUtils.getObjectFromJsonFile(
                jsonFile = "success_nbateams_result.json",
                tClass = TeamsList::class.java
            )

            val liveDataResponse = MutableLiveData<TeamsList>()
            liveDataResponse.value = response

            doReturn(ResultWrapper.SUCCESS(liveDataResponse)).`when`(mockRepository)
                .getNBATeamsList()

            viewModel.nbaTeamsListResponse.observeForever(mockObserver)

            viewModel.fetchNBATeamsList()

            verify(mockRepository, times(2)).getNBATeamsList()
        }
    }

    @Test
    fun `on http success fetch nba teams detail response results are expected`() {
        runTest {
            val response = TestJsonUtils.getObjectFromJsonFile(
                jsonFile = "success_nbateams_detail.json",
                tClass = Team::class.java
            )

            val liveDataResponse = MutableLiveData<Team>()
            liveDataResponse.value = response

            doReturn(ResultWrapper.SUCCESS(liveDataResponse)).`when`(mockRepository)
                .getNBATeamDetails("Foo")

            viewModel.nbaTeamsListResponse.observeForever(mockObserver)

            viewModel.fetchNBATeamDetails("Foo")

            verify(mockRepository, times(1)).getNBATeamDetails("Foo")
        }
    }

    @Test
    fun `on http error fetch nba teams response as empty`() {
        runTest {
            val response = TestJsonUtils.getObjectFromJsonFile(
                jsonFile = "success_nbateams_empty.json",
                tClass = TeamsList::class.java
            )

            doReturn(ResultWrapper.FAILURE(null)).`when`(mockRepository)
                .getNBATeamsList()

            viewModel.nbaTeamsListResponse.observeForever(mockObserver)

            viewModel.fetchNBATeamsList()
        }
    }

    @After
    fun tearDown() {
        viewModel.nbaTeamsListResponse.removeObserver(mockObserver)
    }

    // Reusable JUnit4 TestRule to override the Main dispatcher
    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}