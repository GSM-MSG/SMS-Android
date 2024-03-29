package com.msg.sms.data.remote.dto.stack.reponse

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.stack.DetailStackListModel

data class SearchingDetailStackResponse(
    @SerializedName("techStacks")
    val techStack: List<String>
)

fun SearchingDetailStackResponse.toDetailStackListModel(): DetailStackListModel {
    return DetailStackListModel(techStacks = this.techStack)
}