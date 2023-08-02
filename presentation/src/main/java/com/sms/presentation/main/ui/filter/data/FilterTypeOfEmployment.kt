package com.sms.presentation.main.ui.filter.data

enum class FilterTypeOfEmployment(val value: String, val enum: String) {
    FULL_TIME("정규직", "FULL_TIME"),
    TEMPORARY("비정규직", "TEMPORARY"),
    CONTRACT("계약직", "CONTRACT"),
    INTERN("인턴", "INTERN")
}