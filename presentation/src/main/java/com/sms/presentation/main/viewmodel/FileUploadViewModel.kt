package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
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
    private val imageUploadUseCase: ImageUploadUseCase
) : ViewModel() {
    private val _imageUploadResponse =
        MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val imageUploadResponse: StateFlow<Event<FileUploadResponseModel>> get() = _imageUploadResponse

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
}