package ru.wb.data.datasource

import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor
import ru.wb.domain.model.Profile
import ru.wb.domain.model.toEventVisitor
import java.util.UUID
import kotlin.random.Random

internal class MockIDataSource: IDataSource {

    private var profile = Profile.default

    private val pinCode = Random.nextInt(from = 1000, until = 9999).toString()

    private val loremIpsum = """
                        Lorem ipsum dolor sit amet consectetur. Libero duis cum egestas amet mollis massa. Convallis sit lacus tortor interdum auctor viverra vitae. Egestas aliquam odio aenean eget facilisi ipsum vitae. Risus lectus quam urna condimentum id massa magna id mattis. Sit tempor volutpat ac eget dignissim nibh sagittis vitae duis. Vivamus quis fusce egestas vel sodales arcu praesent non. Ullamcorper elit sit eros egestas euismod amet. Nec molestie a sit sed. At neque diam turpis cursus tincidunt nisi quam sed non. Tempor tortor ultricies ultrices maecenas lectus in nunc sapien dapibus.
                        Volutpat placerat et placerat felis tristique quis. Pharetra velit faucibus lobortis vitae dui. Nibh diam velit hendrerit posuere vel ut augue varius velit. Eu eget ipsum vulputate consectetur adipiscing est mollis eleifend quisque. Porttitor senectus nibh molestie faucibus sit mi risus eget. Vivamus dolor ac tortor nibh. Metus amet odio id magna. Augue ac commodo sem varius purus eros eu pharetra nec.
                        Bibendum eget donec senectus turpis massa. Magna nunc diam pellentesque egestas sit auctor. Ullamcorper placerat blandit eget scelerisque adipiscing nisi tellus. Aliquam aliquet arcu diam cursus. Egestas duis tellus etiam molestie imperdiet. Tellus turpis purus ligula odio at facilisi. Felis sed in adipiscing ut et amet eros at. Tortor tempor habitasse molestie sed enim condimentum. Purus tellus nec lacus nisl eu sit venenatis elit. Nunc at lacus sit iaculis et volutpat. Elit id vulputate non sed placerat neque parturient egestas. Proin pellentesque bibendum volutpat adipiscing sagittis habitant elit.
                        Odio justo dignissim ullamcorper purus ullamcorper sit semper dictum. Tortor est mauris aliquet amet sit ultrices auctor nulla. Faucibus aliquam etiam pharetra pellentesque sagittis odio lacus. Eu morbi senectus in massa fermentum elit in. Tincidunt est blandit malesuada auctor. Orci tellus mus aliquam accumsan ac. Et urna nisl facilisis non volutpat et sodales.
                        Malesuada egestas enim purus cras diam eget vel. Massa ante sit scelerisque scelerisque hac. Consequat tempor non pretium convallis. Interdum iaculis sit interdum interdum magna. Gravida urna et cursus donec consectetur nulla. Aliquet egestas nulla arcu aliquam facilisi duis maecenas viverra. Egestas consectetur mauris orci sit. Bibendum orci at viverra pharetra tortor nulla amet erat vehicula. Mauris volutpat amet in sit rhoncus. Imperdiet feugiat id fames gravida.
                    """.trimIndent()

    private val communityList = listOf(
        Community(
            id = "6c19d92a-7a24-41ab-acb8-2ab6503cf124",
            title = "Вологда",
            description = loremIpsum,
            imageUrl = "https://kassaev.com/media/vologda.jpg",
            size = 318112
        ),
        Community(
            id = "4e9b30e1-b695-4839-aceb-eb6d5e7a092f",
            title = "Пятигорск",
            description = loremIpsum,
            imageUrl = "https://kassaev.com/media/pytigorsk.png",
            size = 213000
        ),
        Community(
            id = "7547580a-e940-4c31-955a-f05dd30dec58",
            title = "Сызрань",
            description = loremIpsum,
            imageUrl = "https://kassaev.com/media/sizran.png",
            size = 165000
        ),
        Community(
            id = "203653fc-1632-49b5-9749-9b45554680e3",
            title = "Воронеж",
            description = loremIpsum,
            imageUrl = "https://kassaev.com/media/voronezh.jpg",
            size = 1048738
        ),
    )

    private val eventList = listOf(
        Event(
            id = UUID.randomUUID()
                .toString(),
            communityId = "7547580a-e940-4c31-955a-f05dd30dec58",
            title = "Android 11",
            description = loremIpsum,
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
            communityId = "203653fc-1632-49b5-9749-9b45554680e3",
            title = "Android 13",
            description = loremIpsum,
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
            communityId = "4e9b30e1-b695-4839-aceb-eb6d5e7a092f",
            title = "Android 14",
            description = loremIpsum,
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
            communityId = "6c19d92a-7a24-41ab-acb8-2ab6503cf124",
            title = "Android 15",
            description = loremIpsum,
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

    override suspend fun setProfile(profile: Profile) {
        this.profile = profile
    }

    private var eventVisitorList = MutableList(10) { Profile.default.toEventVisitor() }

    override suspend fun removeUserFromEventVisitorList(eventId: String): List<EventVisitor> =
        this.eventVisitorList.also {
            it.removeLast()
        }

    override suspend fun getPinCode(): String =
        this.pinCode

    override suspend fun getCommunityList(): List<Community> =
        this.communityList

    override suspend fun addUserToEventVisitorList(eventId: String): List<EventVisitor> =
        this.eventVisitorList.also {
            it += this.profile.toEventVisitor()
        }

    override suspend fun getEventList(): List<Event> =
        this.eventList

    override suspend fun getEventList(communityId: String): List<Event> =
        this.eventList.filter { event ->
            event.communityId == communityId
        }

    override suspend fun getEventById(eventId: String): Event =
        this.eventList.first{ event ->
            event.id == eventId
        }

    override suspend fun getEventVisitorList(): List<EventVisitor> =
        this.eventVisitorList

    override suspend fun getProfile(): Profile =
        this.profile

    override suspend fun getCommunityById(communityId: String): Community =
        this.communityList.first { community ->
            community.id == communityId
        }

}
