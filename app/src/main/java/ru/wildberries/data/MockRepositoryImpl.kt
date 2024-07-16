package ru.wildberries.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wildberries.domain.model.Community
import ru.wildberries.domain.model.Event
import ru.wildberries.domain.Repository
import ru.wildberries.domain.model.Profile
import java.util.UUID

class MockRepositoryImpl : Repository {
    override fun getProfileData(): Profile =
        profile

    override fun setProfileData(profile: Profile){
        this.profile = profile
    }

    override fun getEventList(): Flow<List<Event>> =
        flow {
            emit(
                listOf(
                    Event(
                        id = UUID.randomUUID()
                            .toString(),
                        communityId = UUID.randomUUID()
                            .toString(),
                        title = "Android 11",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_11.png",
                        date = "08.09.2020",
                        location = "Сызрань",
                        isActive = false,
                        tagList = listOf(
                            "Android",
                            "Сызрань"
                        )
                    ),
                    Event(
                        id = UUID.randomUUID()
                            .toString(),
                        communityId = UUID.randomUUID()
                            .toString(),
                        title = "Android 13",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_13.png",
                        date = "15.08.2022",
                        location = "Воронеж",
                        isActive = false,
                        tagList = listOf(
                            "Android",
                            "Воронеж"
                        )
                    ),
                    Event(
                        id = UUID.randomUUID()
                            .toString(),
                        communityId = UUID.randomUUID()
                            .toString(),
                        title = "Android 14",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_14.png",
                        date = "04.10.2023",
                        location = "Пятигорск",
                        isActive = false,
                        tagList = listOf(
                            "Android",
                            "Пятигорск"
                        )
                    ),
                    Event(
                        id = UUID.randomUUID()
                            .toString(),
                        communityId = UUID.randomUUID()
                            .toString(),
                        title = "Android 15",
                        description = LoremIpsum(120).values.first(),
                        imageUrl = "https://kassaev.com/media/android_15.png",
                        date = "01.08.2024",
                        location = "Вологда",
                        isActive = true,
                        tagList = listOf(
                            "Android",
                            "Вологда"
                        )
                    ),
                )
            )
        }

    override fun getCommunityList(): Flow<List<Community>> =
        flow {
            emit(
                listOf(
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Вологда",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/vologda.jpg",
                        size = 318112
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Пятигорск",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/pytigorsk.png",
                        size = 213000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Сызрань",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/sizran.png",
                        size = 165000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Воронеж",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/voronezh.jpg",
                        size = 1048738
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Вологда",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/vologda.jpg",
                        size = 318112
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Пятигорск",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/pytigorsk.png",
                        size = 213000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Сызрань",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/sizran.png",
                        size = 165000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Воронеж",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/voronezh.jpg",
                        size = 1048738
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Вологда",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/vologda.jpg",
                        size = 318112
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Пятигорск",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/pytigorsk.png",
                        size = 213000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Сызрань",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/sizran.png",
                        size = 165000
                    ),
                    Community(
                        id = UUID.randomUUID()
                            .toString(),
                        title = "Воронеж",
                        description = LoremIpsum(117).values.first(),
                        imageUrl = "https://kassaev.com/media/voronezh.jpg",
                        size = 1048738
                    ),
                )
            )
        }

    override fun getEventVisitorList(): Flow<List<Profile>> =
        flow {
            emit(
                visitorList
            )
        }

    override fun setEventVisitorList(visitor: Profile): Flow<List<Profile>> {
        visitorList += visitor
        return flow {
            emit(
                visitorList
            )
        }
    }

    override fun setEventVisitorList(): Flow<List<Profile>> {
        visitorList.removeLast()
        return flow {
            emit(
                visitorList
            )
        }
    }

    private var visitorList = mutableListOf(
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
        Profile(
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            firstName = "Андрей",
            lastName = "Кассаев",
            phoneNumber = "+79967370744",
            id = UUID.randomUUID()
                .toString()
        ),
    )

    var profile = Profile(
        imageUrl = "https://kassaev.com/media/night_sky.jpg",
        firstName = "Андрей",
        lastName = "Кассаев",
        phoneNumber = "+79967370744",
        id = UUID.randomUUID()
            .toString()
    )

}