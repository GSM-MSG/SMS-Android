package com.msg.sms.design.component.segmented_control

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    itemCorner: Int = 4,
    backgroundCorner: Int = 8,
    onItemSelection: (selectedItemIndex: Int) -> Unit,
) {
    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
    val itemIndex = remember { mutableStateOf(defaultSelectedItemIndex) }

    SMSTheme { colors, typography ->
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (selectedIndex.value == itemIndex.value) colors.N10 else colors.P2
            ),
            shape = RoundedCornerShape(backgroundCorner.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .background(colors.N10)
            ) {
                items.forEachIndexed { index, item ->
                    itemIndex.value = index
                    val containerColor = if (selectedIndex.value == index) colors.P2 else colors.N10
                    val contentColor = if (selectedIndex.value == index) colors.N10 else colors.P2
                    val textColor = if (selectedIndex.value == index) colors.WHITE else colors.N40
                    Card(
                        modifier = modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                selectedIndex.value = index
                                onItemSelection(index)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = containerColor,
                            contentColor = contentColor
                        ),
                        shape = when (index) {
                            0 -> RoundedCornerShape(itemCorner.dp)
                            items.size - 1 -> RoundedCornerShape(itemCorner.dp)
                            else -> RoundedCornerShape(0.dp)
                        }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.fillMaxSize(),
                        ) {
                            Text(
                                text = item,
                                style = typography.title2,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewSegmentControl(){
    SegmentedControl(items = listOf("True", "False")) {
    }
}

