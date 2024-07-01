package ru.wildberries.domain

import ru.wildberries.R

data class CommunityModel(
    val img: Int = R.drawable.community,
    val name: String = "Designa",
    val amount: Int = 10000
)
