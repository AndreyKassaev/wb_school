package ru.wildberries.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wildberries.domain.CommunityModel
import ru.wildberries.domain.EventModel
import ru.wildberries.domain.IMockRepository
import ru.wildberries.domain.ProfileModel
import java.util.UUID

class MockRepositoryImpl: IMockRepository {
    override fun getProfileData(): ProfileModel =
        ProfileModel(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = 9967370744,
            id = UUID.randomUUID().toString()
        )

    override fun getEventList(): Flow<List<EventModel>> =
        flow {
            emit(
                listOf(
                    EventModel(
                        id = UUID.randomUUID().toString(),
                        communityId = UUID.randomUUID().toString(),
                        title = "Android 11",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_11.png",
                        date = "08.09.2020",
                        location = "Сызрань",
                        isActive = false,
                        tagList = listOf("Android", "Сызрань")
                    ),
                    EventModel(
                        id = UUID.randomUUID().toString(),
                        communityId = UUID.randomUUID().toString(),
                        title = "Android 13",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_13.png",
                        date = "15.08.2022",
                        location = "Воронеж",
                        isActive = false,
                        tagList = listOf("Android", "Воронеж")
                    ),
                    EventModel(
                        id = UUID.randomUUID().toString(),
                        communityId = UUID.randomUUID().toString(),
                        title = "Android 14",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_14.png",
                        date = "04.10.2023",
                        location = "Пятигорск",
                        isActive = false,
                        tagList = listOf("Android", "Пятигорск")
                    ),
                    EventModel(
                        id = UUID.randomUUID().toString(),
                        communityId = UUID.randomUUID().toString(),
                        title = "Android 15",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_15.png",
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
                        id = UUID.randomUUID().toString(),
                        title = "Вологда",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/vologda.jpg",
                        amount = 318112
                    ),
                    CommunityModel(
                        id = UUID.randomUUID().toString(),
                        title = "Пятигорск",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/pytigorsk.png",
                        amount = 213000
                    ),
                    CommunityModel(
                        id = UUID.randomUUID().toString(),
                        title = "Сызрань",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/sizran.png",
                        amount = 165000
                   ),
                    CommunityModel(
                        id = UUID.randomUUID().toString(),
                        title = "Воронеж",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/voronezh.jpg",
                        amount = 1048738
                    ),
                )
            )
        }

    override fun getEventVisitorList(): Flow<List<ProfileModel>> =
        flow {
            emit(
                listOf(
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                    ProfileModel(
                        imageUrl = "https://kassaev.com/media/night_sky.jpg",
                        firstName = "Андрей",
                        lastName = "Кассаев",
                        phoneNumber = 9967370744,
                        id = UUID.randomUUID().toString()
                    ),
                )
            )
        }

}