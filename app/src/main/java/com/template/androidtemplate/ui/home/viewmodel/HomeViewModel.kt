package com.template.androidtemplate.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.androidtemplate.data.model.GameOfThrones
import com.template.androidtemplate.data.repository.HomeRepository
import com.template.androidtemplate.utils.NetworkHelper
import com.template.androidtemplate.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val homeRepository: HomeRepository

) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData()

    var onResponse: MutableLiveData<Resource<List<GameOfThrones>>> = MutableLiveData()

    fun isProgressBarVisible(): LiveData<Boolean> {
        return progressBarVisibility
    }

    fun getGameOfThronesData(): LiveData<Resource<List<GameOfThrones>>>{
        return onResponse
    }

    init {
        doGameOfThronesApiWork();
    }

    private fun doGameOfThronesApiWork() {

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                homeRepository.gameOfThrones().let {
                    if (it.isSuccessful){
                        onResponse.postValue(Resource.success(it.body()))
                        progressBarVisibility.postValue(false)
                    }else{
                        onResponse.postValue(Resource.error(it.errorBody().toString(),null))
                        progressBarVisibility.postValue(false)
                    }
                }
            }
        }

    }

}