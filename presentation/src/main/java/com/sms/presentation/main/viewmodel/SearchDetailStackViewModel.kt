package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.stack.DetailStackListModel
import com.msg.sms.domain.usecase.stack.GetSearchDetailStackUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailStackViewModel @Inject constructor(
    private val searchDetailStackUseCase: GetSearchDetailStackUseCase,
) : ViewModel() {
    private val _searchResultEvent = MutableStateFlow<Event<DetailStackListModel>>(Event.Loading)
    val searchResultEvent = _searchResultEvent.asStateFlow()

    fun searchDetailStack(name: String) {
        viewModelScope.launch {
            _searchResultEvent.value = Event.Loading
            searchDetailStackUseCase(name).onSuccess {
                it.catch { remoteError ->
                    _searchResultEvent.value = remoteError.errorHandling()
                }.collect { response ->
                    _searchResultEvent.value = Event.Success(data = response)
                    _searchResultEvent.value = Event.Loading
                }
            }.onFailure {
                _searchResultEvent.value = it.errorHandling()
            }
        }
    }
}