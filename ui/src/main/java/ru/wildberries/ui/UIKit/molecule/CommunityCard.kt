package ru.wildberries.ui.UIKit.molecule

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wb.domain.model.Community
import ru.wildberries.R
import ru.wildberries.ui.UIKit.atom.Avatar
import ru.wildberries.ui.theme.WBTheme
import java.util.Locale

@Composable
fun CommunityCard(
    community: Community
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Avatar(imageUrl = community.imageUrl)
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(
                text = community.title,
                style = WBTheme.typography.bodyText1,
                color = WBTheme.colors.NeutralActive,
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                text = communityCardAmountFormatter(amount = community.size),
                style = WBTheme.typography.metadata1,
                color = WBTheme.colors.NeutralDisabled
            )
        }
    }
}

@Preview
@Composable
private fun CommunityCardPreview() {
    WBTheme {
        CommunityCard(community = Community.default)
    }
}

@Composable
fun communityCardAmountFormatter(amount: Int): String {
    val dec = DecimalFormat("###,###,###,###,###", DecimalFormatSymbols(Locale.ENGLISH))
    val formattedNumber = dec.format(amount).replace(",", " ")
    return "$formattedNumber ${stringResource(R.string.people)}"
}
