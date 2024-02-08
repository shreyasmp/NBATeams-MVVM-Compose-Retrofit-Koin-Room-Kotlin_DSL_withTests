package com.shreyasmp.nbateams.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.shreyasmp.nbateams.base.MockServerBaseTest
import com.shreyasmp.nbateams.database.NBATeamsDao
import com.shreyasmp.nbateams.service.NBATeamService
import com.shreyasmp.nbateams.utils.ResultWrapper
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class NBATeamsRepositoryTest : MockServerBaseTest() {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    override fun isMockServerEnabled(): Boolean = true

    private lateinit var repository: NBATeamsRepository
    private lateinit var service: NBATeamService
    @Mock
    private lateinit var dao: NBATeamsDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        service = provideNBATeamsService()
        repository = NBATeamsRepositoryImpl(service, dao, context)
    }

    @Test
    fun `given response is 200 when fetching nba teams response and returns success`() =
        runTest {
            mockHttpResponseFromFile("success_nbateams_result.json", HttpURLConnection.HTTP_OK)
            when (val result = repository.getNBATeamsList()) {
                is ResultWrapper.SUCCESS -> {
                    val nbateams = result.value
                    assertThat(nbateams).isNotNull()
                    assertThat(nbateams?.size).isEqualTo(30)
                }

                else -> {}
            }
        }

    @Test
    fun `given response is 200 when fetching nba teams response and returns empty list response`() =
        runTest {
            mockHttpResponseFromFile("success_nbateams_empty.json", HttpURLConnection.HTTP_OK)
            when (val result = repository.getNBATeamsList()) {
                is ResultWrapper.SUCCESS -> {
                    val nbateams = result.value
                    assertThat(nbateams).isNull()
                }

                else -> {}
            }
        }

    @Test
    fun `given response is 403 when fetching nba teams response and returns exception`() =
        runTest {
            mockHttpResponse(403)
            when (val result = repository.getNBATeamsList()) {
                is ResultWrapper.FAILURE -> {
                    assertThat(result).isNotNull()
                    val expectedResponse = ResultWrapper.FAILURE(null)
                    assertThat(expectedResponse.code).isEqualTo(result.code)
                }

                else -> {}
            }
        }
}