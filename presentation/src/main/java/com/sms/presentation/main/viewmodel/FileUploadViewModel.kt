package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
import com.msg.sms.domain.usecase.fileupload.DreamBookUploadUseCase
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class FileUploadViewModel @Inject constructor(
    private val imageUploadUseCase: ImageUploadUseCase,
    private val dreamBookUploadUseCase: DreamBookUploadUseCase
) : ViewModel() {
    private val _imageUploadResponse = MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val imageUploadResponse: StateFlow<Event<FileUploadResponseModel>> get() = _imageUploadResponse

    private val _dreamBookUploadResponse = MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val dreamBookUploadResponse: StateFlow<Event<FileUploadResponseModel>> get() = _dreamBookUploadResponse

    fun imageUpload(file: MultipartBody.Part) = viewModelScope.launch {
        imageUploadUseCase(
            file = file
        ).onSuccess {
            it.catch { remoteError ->
                _imageUploadResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _imageUploadResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _imageUploadResponse.value = error.errorHandling()
        }
    }

    fun dreamBookUpload(file: MultipartBody.Part) = viewModelScope.launch {
        dreamBookUploadUseCase(
            file = file
        ).onSuccess {
            it.catch { remoteError ->
                _dreamBookUploadResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _dreamBookUploadResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _dreamBookUploadResponse.value = error.errorHandling()
        }
    }
}