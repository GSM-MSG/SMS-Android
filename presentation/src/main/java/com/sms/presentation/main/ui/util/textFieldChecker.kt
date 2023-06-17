package com.sms.presentation.main.ui.util


fun textFieldChecker(vararg textField: String): Boolean {
    return textField.none { it.trim() != "" }
}