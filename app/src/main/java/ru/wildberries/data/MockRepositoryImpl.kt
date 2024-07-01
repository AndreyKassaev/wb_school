package ru.wildberries.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wildberries.R
import ru.wildberries.domain.CommunityModel
import ru.wildberries.domain.EventModel
import ru.wildberries.domain.IMockRepository
import ru.wildberries.domain.ProfileModel

class MockRepositoryImpl: IMockRepository {
    override fun getProfileData(): ProfileModel =
        ProfileModel(
            image = R.drawable.android,
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+7 996 737 07 44"
        )

    override fun getEventList(): Flow<List<EventModel>> =
        flow {
            emit(
                listOf(
                    EventModel(
                        img = R.drawable.android_11,
                        name = "Android 11",
                        date = "08.09.2020",
                        location = "Сызрань",
                        isActive = false,
                        tagList = listOf("Android", "Сызрань")
                    ),
                    EventModel(
                        img = R.drawable.android_13,
                        name = "Android 13",
                        date = "15.08.2022",
                        location = "Воронеж",
                        isActive = false,
                        tagList = listOf("Android", "Воронеж")
                    ),
                    EventModel(
                        img = R.drawable.android,
                        name = "Android 14",
                        date = "04.10.2023",
                        location = "Пятигорск",
                        isActive = false,
                        tagList = listOf("Android", "Пятигорск")
                    ),
                    EventModel(
                        img = R.drawable.android_15,
                        name = "Android 15",
                        date = "01.08.2024",
                        location = "Вологда",
                        isActive = true,
                        tagList = listOf("Android", "Вологда")
                    ),
                )
            )
        }

    override fun getCommunityList(): Flow<List<CommunityModel>> =
        flow {
            emit(
                listOf(
                    CommunityModel(
                        img = R.drawable.community,
                        name = "Designa",
                        amount = 10000
                    ),
                    CommunityModel(
                        img = R.drawable.community,
                        name = "Designa",
                        amount = 10000
                    ),
                    CommunityModel(
                        img = R.drawable.community,
                        name = "Designa",
                        amount = 10000
                    ),
                    CommunityModel(
                        img = R.drawable.community,
                        name = "Designa",
                        amount = 10000
                    ),
                    CommunityModel(
                        img = R.drawable.community,
                        name = "Designa",
                        amount = 10000
                    ),
                )
            )
        }

}