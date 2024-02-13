package com.sms.presentation.main.ui.fill_out_authentication.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.fill_out_authentication.component.ActivityDescriptionComponent
import com.sms.presentation.main.ui.fill_out_authentication.component.ActivityTitleComponent
import com.sms.presentation.main.ui.fill_out_authentication.component.PicturePickerComponent
import com.sms.presentation.main.ui.fill_out_authentication.state.AuthenticationData

@Composable
fun AuthenticationSection(
    modifier: Modifier,
    authenticationData: AuthenticationData,
    onValueChange: (value: AuthenticationData) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PicturePickerComponent(
            onChangeMyProfileImage = { onValueChange(authenticationData.copy(activityImagesBitmap = it)) },
            imageUrl = authenticationData.activityImages,
            bitmapImage = authenticationData.activityImagesBitmap,
        )
        ActivityTitleComponent(
            activityTitleValue = authenticationData.title,
            onValueChange = { onValueChange(authenticationData.copy(title = it)) }
        )
        ActivityDescriptionComponent(
            modifier = Modifier.weight(1f),
            activityDescriptionValue = authenticationData.content,
            onValueChange = { onValueChange(authenticationData.copy(content = it)) }
        )
    }
}