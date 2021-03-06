package dev.jamile.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jamile.newsapp.common.FlowState
import dev.jamile.newsapp.common.ProviderContext
import dev.jamile.newsapp.common.postError
import dev.jamile.newsapp.common.postSuccess
import dev.jamile.newsapp.domain.GetListTopNewsUseCase
import dev.jamile.newsapp.domain.core.ParametersDTO
import dev.jamile.newsapp.domain.model.News
import kotlinx.coroutines.launch

private const val COUNTY_CODE = "br"

class NewsViewModel(
    private val getListTopNewsUseCase: GetListTopNewsUseCase,
    private val providerContext: ProviderContext
) : ViewModel() {
    private val _newsList = MutableLiveData<FlowState<List<News>>>()
    val newList: LiveData<FlowState<List<News>>> = _newsList

    fun fetchNews() {
        viewModelScope.launch(providerContext.main) {
            getListTopNewsUseCase.execute(
                parameters = ParametersDTO{ add("country", COUNTY_CODE) },
                onSuccess = { newsList ->
                    _newsList.postSuccess(newsList)
                },
                onError = {
                    _newsList.postError(it)
                }
            )
        }
    }
}