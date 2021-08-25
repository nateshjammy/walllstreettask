package com.wallstreet.main.home.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wallstreet.data.LocalDatabase.RoomDataBase
import com.wallstreet.data.model.University
import com.wallstreet.data.repository.MainRepository
import com.wallstreet.data.repository.UniversityDatasourceFactory
import kotlinx.coroutines.launch
class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var universityList: LiveData<PagedList<University>>? = null

    var searchString: MutableLiveData<String> = MutableLiveData()

    var datasourcefactory : UniversityDatasourceFactory ?=null

    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val mShowProgress: LiveData<Boolean>
        get() = showProgress




    private  fun getUniversity(country: String = ""){

        viewModelScope.launch {
            showProgress.postValue(true)
            mainRepository.getUniversity()
            showProgress.postValue(false)
        }

    }


    init {
        getUniversity()
        RoomDataBase.instance?.let {
            datasourcefactory = UniversityDatasourceFactory(it.universitydao())

            val config = PagedList.Config.Builder()
                    .setPageSize(30)
                    .setInitialLoadSizeHint(50)
                    .setPrefetchDistance(10)
                    .build()

             datasourcefactory?.let {

                universityList = LivePagedListBuilder(it, config).build()
            }
        }
    }

    fun refresh() {
        datasourcefactory?.refreshData(searchString.value?:"")
        universityList?.value?.dataSource?.invalidate()

    }

 }






