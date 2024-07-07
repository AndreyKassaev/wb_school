package ru.wildberries.ui.UIKit.organism

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.ActivityContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    viewModel: MainViewModel
) {
    val title = viewModel.topAppBarArg.title
    val navigationIcon = viewModel.topAppBarArg.navigationIcon
    val navigationIconOnClick = viewModel.topAppBarArg.navigationIconOnClick
    val actionIcon = viewModel.topAppBarArg.actionIcon
    val actionIconOnclick = viewModel.topAppBarArg.actionIconOnclick

    TopAppBar(
        title = {
            if (title != null){
                Text(
                    modifier = Modifier
                        .padding(start = if (navigationIcon == null) 12.dp else 0.dp),
                    text = title,
                    style = WBTheme.typography.subHeading1,
                    color = WBTheme.colors.NeutralActive
                )
            }
        },
        navigationIcon = {
            if (navigationIcon != null){
                IconButton(
                    onClick = {
                        navigationIconOnClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = navigationIcon),
                        contentDescription = "Go Back"
                    )
                }
            }
        },
        actions = {
            if (actionIcon != null){
                IconButton(
                    onClick = {
                        actionIconOnclick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = actionIcon),
                        contentDescription = "Edit",
                        tint = WBTheme.colors.NeutralActive
                    )
                }
            }
        }
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun TopBarPreview() {
    WBTheme {
        TopBar(
            viewModel = MainViewModel(MockRepositoryImpl())
        )
    }
}
data class TopBarArg(
    var title: String,
    val navigationIcon: Int?,
    val navigationIconOnClick: () -> Unit = {},
    val actionIcon: Int?,
    val actionIconOnclick: () -> Unit = {}
){
    companion object {
        val default = TopBarArg(
            title = getString(ActivityContext.context, R.string.app_name),
            navigationIcon = null,
            actionIcon = null,
        )
    }
}