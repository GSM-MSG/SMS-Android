package com.msg.sms.design.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sms.design_system.R

@Composable
fun BackButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_back_btn),
        contentDescription = "Back Button Icon",
        modifier = modifier
    )
}

@Composable
fun CameraIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_camera),
        contentDescription = "Camera Icon",
        modifier = modifier
    )
}

@Composable
fun CheckButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_check_btn),
        contentDescription = "Check Button Icon",
        modifier = modifier
    )
}

@Composable
fun DeleteButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_delete_btn),
        contentDescription = "Delete Button Icon",
        modifier = modifier
    )
}

@Composable
fun FilledPlusButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_filled_plus_btn),
        contentDescription = "Filled Plus Button Icon",
        modifier = modifier
    )
}

@Composable
fun OpenButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_open_btn),
        contentDescription = "Open Button Icon",
        modifier = modifier
    )
}

@Composable
fun PlusButtonIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_plus_btn),
        contentDescription = "Plus Button Icon",
        modifier = modifier
    )
}

@Composable
fun PlusButtonGrayIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_plus_btn_gray),
        contentDescription = "Plus Button Gray Icon",
        modifier = modifier
    )
}

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_profile_btn),
        contentDescription = "ProfileButton",
        modifier = modifier
    )
}

@Composable
fun TrashCanIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_trash_can),
        contentDescription = "Trash Can Icon Button",
        modifier = modifier
    )
}




