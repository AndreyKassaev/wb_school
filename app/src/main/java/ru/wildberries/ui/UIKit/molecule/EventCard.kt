package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.domain.EventModel
import ru.wildberries.ui.UIKit.atom.Avatar
import ru.wildberries.ui.theme.WBTheme

@Composable
fun EventCard(
    eventModel: EventModel = EventModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Avatar(img = eventModel.img)
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 3.dp),
                    style = WBTheme.typography.bodyText1,
                    color = WBTheme.colors.NeutralActive,
                    text = eventModel.name
                )
                Text(
                    style = WBTheme.typography.metadata2,
                    color = WBTheme.colors.NeutralWeak,
                    text = if (eventModel.isActive) "" else "Закончилась"
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 6.dp),
                style = WBTheme.typography.metadata1,
                color = WBTheme.colors.NeutralWeak,
                text = "${eventModel.date} - ${eventModel.location}"
            )
            TagsRow(tagList = eventModel.tagList)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun EventCardPreview(){
    WBTheme {
        EventCard()
    }
}