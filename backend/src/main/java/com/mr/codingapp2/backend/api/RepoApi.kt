package com.mr.codingapp2.backend.api

import com.mr.codingapp2.backend.model.Repository
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {

    @GET("/users/{username}/repos")
    fun listRepos(
        @Path("username") username: String,
        @Query("per_page") perPage: Int = 100
    ): Observable<ArrayList<Repository>>
}
