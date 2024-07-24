package ru.wildberries.ui.UIKit.organism

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.ui.theme.WBTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String? = null,
    navigationIcon: Int? = null,
    actionIcon: @Composable () -> Unit = {},
    navigationIconOnClick: ()->Unit = {},
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (navigationIcon != null) {
                IconButton(
                    onClick = {
                        navigationIconOnClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = navigationIcon),
                        contentDescription = null,
                        tint = WBTheme.colors.NeutralActive
                    )
                }
            }
            if (title != null) {
                Text(
                    text = title,
                    style = WBTheme.typography.subHeading1,
                    color = WBTheme.colors.NeutralActive,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            actionIcon()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TopBarPreview() {
    WBTheme {
        TopBar(
            title = "Title",
            navigationIcon = R.drawable.arrow_back,
        )
    }
}