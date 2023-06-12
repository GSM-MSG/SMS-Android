package com.msg.sms.data.remote.dto.stack.reponse

import com.msg.sms.domain.model.stack.DetailStackListModel

data class SearchingDetailStackResponse(
    val techStack: List<String>
)

fun SearchingDetailStackResponse.toDetailStackListModel(): DetailStackListModel {
    return DetailStackListModel(techStack = this.techStack)
}