package com.msg.sms.design.component.button

sealed class ButtonState {
    object OutLine: ButtonState()
    object Normal: ButtonState()

    object Error: ButtonState()
}