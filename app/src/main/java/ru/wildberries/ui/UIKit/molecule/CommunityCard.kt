package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.domain.CommunityModel
import ru.wildberries.ui.UIKit.atom.Avatar
import ru.wildberries.ui.theme.WBTheme


@Preview(
    showBackground = true
)
@Composable
fun CommunityCard(
    communityModel: CommunityModel = CommunityModel()
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
    ) {
        Avatar(img = communityModel.img)
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(
                text = communityModel.name,
                style = WBTheme.typography.bodyText1,
                color = WBTheme.colors.NeutralActive,
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                text = CommunityCardAmountFormatter(amount = communityModel.amount),
                style = WBTheme.typography.metadata1,
                color = WBTheme.colors.NeutralDisabled
            )
        }
    }
}

fun CommunityCardAmountFormatter(amount: Int): String {
    return if (amount > 999) "${StringBuilder(amount.toString()).insert(2, " ")} человек"
        else
            "amount человек"
}
