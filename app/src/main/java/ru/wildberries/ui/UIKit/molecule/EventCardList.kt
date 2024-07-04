package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.wildberries.domain.EventModel
import ru.wildberries.ui.theme.WBTheme

@Composable
fun EventCardList(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        repeat(5){ times ->
            EventCard(if (times % 2 == 0) EventModel() else EventModel(isActive = false))
            HorizontalDivider(
                color = WBTheme.colors.NeutralLine
            )
        }
    }
}