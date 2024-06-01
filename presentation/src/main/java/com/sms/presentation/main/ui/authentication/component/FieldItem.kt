package com.sms.presentation.main.ui.authentication.component

import com.sms.presentation.main.ui.authentication.enum.InputItemEnum

data class SectionItem(
    val section: String, // 항목 (e.g. 수상경력, 자격증, TOPCIT),
    val description: String?, // 대충 섹션 상세 설명
    val fieldScore: Int, // field 하나당의 점수
    val maxCount: Int, // 필드의 최대 추가 가능 개수, e.g. 한자 자격증 == 1, 독서 == 10, 활동영역 == 8
    val fields: List<FieldItem>,
)

data class FieldItem(
    val name: String,
    val type: InputItemEnum,
    val values: List<String>?,
    val placeHolder: String?,
)
