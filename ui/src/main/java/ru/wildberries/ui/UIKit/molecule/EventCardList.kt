package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import ru.wb.domain.model.Event
import ru.wildberries.ui.theme.WBTheme

@Composable
fun EventCardList(
    eventList: List<Event>
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