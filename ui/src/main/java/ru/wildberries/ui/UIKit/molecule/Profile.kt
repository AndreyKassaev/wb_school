package ru.wildberries.ui.UIKit.molecule

import android.telephony.PhoneNumberUtils
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
import ru.wb.domain.model.Profile
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.theme.WBTheme
import java.util.Locale

@Composable
fun Profile(
    profileData: Profile,
    profileState: ProfileState,
    size: Dp,
    onClick: ()->Unit,
    profileMode: ProfileMode
) {
    when (profileMode) {
        ProfileMode.Row -> {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage(
                    size = 50.dp,
                    profileState = profileState,
                    imageUrl = profileData.imageUrl,
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
                        text = PhoneNumberUtils.formatNumber(profileData.phoneNumber, Locale.getDefault().country),
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
                imageUrl = profileData.imageUrl,
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
                text = PhoneNumberUtils.formatNumber(profileData.phoneNumber, Locale.getDefault().country),
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
private fun ProfilePreview() {
    WBTheme{
        Profile(
            profileData = Profile.default,
            profileState = ProfileState.None,
            size = 200.dp,
            onClick = {},
            profileMode= ProfileMode.Row
        )
    }
}