package ru.wildberries.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.theme.WBTheme

@Composable
fun MainScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 16.dp, end = 8.dp)
        ) {
            SearchBar()
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
    WBTheme {
        MainScreen()
    }
}