package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.ui.theme.WBTheme

@Composable
fun SearchBar(

) {
    val textFieldState = rememberTextFieldState()
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val iconTint = if (textFieldState.text.isEmpty()) WBTheme.colors.NeutralDisabled else WBTheme.colors.NeutralActive
    val border = BorderStroke(width = if (isFocused && textFieldState.text.isEmpty()) 2.dp else 0.dp, color = if (isFocused && textFieldState.text.isEmpty()) WBTheme.colors.NeutralDisabled else Color.Transparent)
    val cursor = if (isFocused && textFieldState.text.isNotEmpty()) SolidColor(WBTheme.colors.NeutralActive) else SolidColor(Color.Transparent)

    BasicTextField(
        cursorBrush = cursor,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                border = border,
                shape = RoundedCornerShape(4.dp)
            )
            .background(WBTheme.colors.NeutralOffWhite)
            .padding(6.dp)
            .focusable(interactionSource = interactionSource),
        interactionSource = interactionSource,
        state = textFieldState,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = WBTheme.typography.bodyText1.copy(color = WBTheme.colors.NeutralActive),
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search icon",
                    tint = iconTint
                )
                Box(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f),
                ){
                    if (textFieldState.text.isEmpty()){
                        innerTextField()
                        Text(
                            text = "Поиск",
                            style = WBTheme.typography.bodyText1,
                            color = WBTheme.colors.NeutralDisabled
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(
    showBackground =true
)
@Composable
private fun SearchBarPreview() {
    SearchBar()
}