package ru.wildberries.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        var out = ""
        text.forEachIndexed { index, char ->
            out += text[index]
            when(index){
                2 -> out += " "
                4 -> out += "-"
                6 -> out += "-"
            }
        }

        val offsetMapping = object: OffsetMapping {
            override fun originalToTransformed(offset: Int) =
                when{
                    offset <= 2 -> offset
                    offset <= 4 -> offset + 1
                    offset <= 6 -> offset + 2
                    offset <= 9 -> offset + 3
                    else -> 12
                }

            override fun transformedToOriginal(offset: Int) =
                when{
                    offset <= 3 -> offset
                    offset <= 6 -> offset - 1
                    offset <= 9 -> offset - 2
                    offset <= 12 -> offset - 3
                    else -> 9
                }

        }
        return TransformedText(
            text = AnnotatedString(text = out),
            offsetMapping = offsetMapping
        )
    }
}