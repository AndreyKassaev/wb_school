package ru.wildberries.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

internal class PhoneNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        //999 999-99-99
        var out = ""
        text.forEachIndexed { index, char ->
            out += text[index]
            when(index){
                2 -> out += " "
                5 -> out += "-"
                7 -> out += "-"
            }
        }

        val offsetMapping = object: OffsetMapping {
            override fun originalToTransformed(offset: Int) =
                when{
                    offset <= 2 -> offset
                    offset <= 5 -> offset + 1
                    offset <= 7 -> offset + 2
                    offset <= 9 -> offset + 3
                    else -> 13
                }
            //999 999-99-99
            override fun transformedToOriginal(offset: Int) =
                when{
                    offset <= 3 -> offset
                    offset <= 7 -> offset - 1
                    offset <= 10 -> offset - 2
                    offset <= 13 -> offset - 3
                    else -> 13
                }

        }
        return TransformedText(
            text = AnnotatedString(text = out),
            offsetMapping = offsetMapping
        )
    }
}