package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.wildberries.ui.UIKit.atom.Avatar
import ru.wildberries.ui.model.EventVisitor
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun EventVisitorAvatarList(
    eventVisitorList: List<EventVisitor>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy((-16).dp)
        ) {
            eventVisitorList.take(5).forEachIndexed { index, user ->
                Avatar(
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                2.dp, WBTheme.colors.Gradient02
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .zIndex((index*(-1)).toFloat()),
                    imageUrl = user.imageUrl
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 14.dp),
            text = if (eventVisitorList.size <= 5) "" else "+${eventVisitorList.size - 5}",
            style = WBTheme.typography.bodyText1,
            color = WBTheme.colors.NeutralActive
        )
    }
}