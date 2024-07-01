package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.wildberries.domain.ProfileModel
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.theme.WBTheme

@Composable
fun Profile(
    profileData: ProfileModel,
    profileState: ProfileState = ProfileState.None,
    size: Dp = 200.dp,
    onClick: ()->Unit = {},
    profileMode: ProfileMode = ProfileMode.Row
) {
    when (profileMode) {
        ProfileMode.Row -> {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage(
                    size = 50.dp,
                    profileState = profileState,
                    image = profileData.image,
                    onClick = onClick
                )
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "${profileData.firstName} ${profileData.lastName}",
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                    Text(
                        text = profileData.phoneNumber,
                        style = WBTheme.typography.metadata1,
                        color = WBTheme.colors.NeutralDisabled
                    )
                }
            }
        }
        ProfileMode.FullScreen -> {
            ProfileImage(
                size = size,
                profileState = profileState,
                image = profileData.image,
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "${profileData.firstName} ${profileData.lastName}",
                style = WBTheme.typography.heading2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = WBTheme.colors.NeutralActive
            )
            Text(
                text = profileData.phoneNumber,
                style = WBTheme.typography.subHeading2.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = WBTheme.colors.NeutralDisabled
            )
        }
    }
}

enum class ProfileMode {
    Row,
    FullScreen
}

@Preview(
    showBackground = true
)
@Composable
private fun Profile() {
    WBTheme{
        Profile(
            profileData = ProfileModel()
        )
    }
}