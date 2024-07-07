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
fun EventCardList(
    eventList: List<EventModel>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        eventList.forEach { event ->
            EventCard(event)
            HorizontalDivider(
                color = WBTheme.colors.NeutralLine
            )
        }
    }
}