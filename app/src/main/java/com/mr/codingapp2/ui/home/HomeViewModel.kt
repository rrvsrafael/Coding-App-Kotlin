package com.mr.codingapp2.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import autodispose2.autoDispose
import com.mr.codingapp2.backend.api.RepoApi
import com.mr.codingapp2.backend.model.Repository
import com.mr.codingapp2.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val app: Application,
    private val repoApi: RepoApi
) : BaseViewModel() {
    val reposLiveData: LiveData<List<Repository>> = MutableLiveData()

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        reposLiveData as MutableLiveData
        repoApi.listRepos("nekocode")
            .retry()
            .map { repos ->
                repos.sortedByDescending { it.stargazersCount }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe({ repos ->
                reposLiveData.postValue(repos)
            }, { err ->
                Toast.makeText(app, err.message, Toast.LENGTH_SHORT).show()
            })
    }
}
