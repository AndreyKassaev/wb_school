package ru.wildberries.ui.screen.lesson

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.ui.UIKit.atom.Avatar
import ru.wildberries.ui.UIKit.atom.GhostButton
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.UIKit.atom.SecondaryButton
import ru.wildberries.ui.UIKit.molecule.TagRow
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun FirstLessonScreen(
    viewModel: LessonViewModel = koinViewModel(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    val eventList by viewModel.getEventListFlow()
        .collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Buttons
        item {
            ButtonsRow()
            ButtonsRow(enabled = false)
        }

        //Typography
        item {
            TypographyList()
        }

        //Avatars
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage(
                    profileState = ProfileState.None,
                    imageUrl = null,
                    size = 100.dp,
                    onClick = {}
                )
                Avatar(
                    imageUrl = eventList.first().imageUrl
                )
            }
        }

        //SearchBar
        item {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
        }

        //Chips
        item {
            TagRow(
                tagList = listOf("Moscow", "Python", "Junior")
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

//Buttons
@Composable
fun ButtonsRow(
    enabled: Boolean = true
) {
    val buttonOnClick =  { println("Clicked") }
    val buttonText = "Button"

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PrimaryButton(
            content = {
                Text(text = buttonText)
            },
            onClick = buttonOnClick,
            isEnabled = enabled
        )
        SecondaryButton(
            content = {
                Text(text = buttonText)
            },
            onClick = buttonOnClick,
            isEnabled = enabled
        )
        GhostButton(
            content = {
                Text(text = buttonText)
            },
            onClick = buttonOnClick,
            isEnabled = enabled
        )
    }
}

//Typography
@Composable
fun TypographyRow(
    textStyle: TextStyle,
    title: String,
    subTitle: String
) {
    val text = "The quick brown fox jumps over the lazy dog"
    LazyRow(
        modifier = Modifier
            .sizeIn(minHeight = 64.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .widthIn(200.dp)
            ) {
                Text(
                    text = title,
                    style = WBTheme.typography.subHeading1,
                    color = WBTheme.colors.NeutralActive,
                )
                Text(
                    text = subTitle,
                    style = WBTheme.typography.bodyText2,
                    color = WBTheme.colors.NeutralDisabled,
                )
            }
        }
        item {
            Text(
                text = text,
                color = WBTheme.colors.NeutralActive,
                style = textStyle,
            )
        }
    }
}

@Composable
fun TypographyList() {
    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TypographyRow(
            textStyle = WBTheme.typography.heading1,
            title = "Heading 1",
            subTitle = "SF Pro Display/32/Bold"
        )
        TypographyRow(
            textStyle = WBTheme.typography.heading2,
            title = "Heading 2",
            subTitle = "SF Pro Display/24/Bold"
        )
        TypographyRow(
            textStyle = WBTheme.typography.subHeading1,
            title = "Subheading 1",
            subTitle = "SF Pro Display/18/SemiBold"
        )
        TypographyRow(
            textStyle = WBTheme.typography.subHeading2,
            title = "Subheading 2",
            subTitle = "SF Pro Display/16/SemiBold"
        )
        TypographyRow(
            textStyle = WBTheme.typography.bodyText1,
            title = "Body Text 1",
            subTitle = "SF Pro Display/14/SemiBold"
        )
        TypographyRow(
            textStyle = WBTheme.typography.bodyText2,
            title = "Body Text 2",
            subTitle = "SF Pro Display/14/Regular"
        )
        TypographyRow(
            textStyle = WBTheme.typography.metadata1,
            title = "Metadata 1",
            subTitle = "SF Pro Display/12/Regular"
        )
        TypographyRow(
            textStyle = WBTheme.typography.metadata2,
            title = "Metadata 2",
            subTitle = "SF Pro Display/10/Regular"
        )
        TypographyRow(
            textStyle = WBTheme.typography.metadata3,
            title = "Metadata 3",
            subTitle = "SF Pro Display/10/SemiBold"
        )
    }
}