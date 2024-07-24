package ru.wb.data.repository

import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor
import ru.wb.domain.model.Profile
import ru.wb.domain.model.toEventVisitor
import ru.wb.domain.repository.IEventRepository
import java.util.UUID

internal class EventRepository: IEventRepository {

    override suspend fun getAllEventList(): List<Event> =
        eventList

    override suspend fun getEventById(eventId: String): Event =
        eventList.first { event ->
            event.id == eventId
        }

    override suspend fun getPersonalEventList(): List<Event> =
        eventList

    override suspend fun getEventVisitorList(eventId: String): List<EventVisitor> =
        visitorList

    override suspend fun acceptEventInvitation(eventId: String): List<EventVisitor> =
        visitorList.also {
            it += Profile.default.toEventVisitor()
        }

    override suspend fun revokeEventInvitation(eventId: String): List<EventVisitor> =
        visitorList.also {
            it.removeLast()
        }

    private var visitorList = MutableList(10) { Profile.default.toEventVisitor() }

    val loremIpsum = """
                        Lorem ipsum dolor sit amet consectetur. Libero duis cum egestas amet mollis massa. Convallis sit lacus tortor interdum auctor viverra vitae. Egestas aliquam odio aenean eget facilisi ipsum vitae. Risus lectus quam urna condimentum id massa magna id mattis. Sit tempor volutpat ac eget dignissim nibh sagittis vitae duis. Vivamus quis fusce egestas vel sodales arcu praesent non. Ullamcorper elit sit eros egestas euismod amet. Nec molestie a sit sed. At neque diam turpis cursus tincidunt nisi quam sed non. Tempor tortor ultricies ultrices maecenas lectus in nunc sapien dapibus.
                        Volutpat placerat et placerat felis tristique quis. Pharetra velit faucibus lobortis vitae dui. Nibh diam velit hendrerit posuere vel ut augue varius velit. Eu eget ipsum vulputate consectetur adipiscing est mollis eleifend quisque. Porttitor senectus nibh molestie faucibus sit mi risus eget. Vivamus dolor ac tortor nibh. Metus amet odio id magna. Augue ac commodo sem varius purus eros eu pharetra nec.
                        Bibendum eget donec senectus turpis massa. Magna nunc diam pellentesque egestas sit auctor. Ullamcorper placerat blandit eget scelerisque adipiscing nisi tellus. Aliquam aliquet arcu diam cursus. Egestas duis tellus etiam molestie imperdiet. Tellus turpis purus ligula odio at facilisi. Felis sed in adipiscing ut et amet eros at. Tortor tempor habitasse molestie sed enim condimentum. Purus tellus nec lacus nisl eu sit venenatis elit. Nunc at lacus sit iaculis et volutpat. Elit id vulputate non sed placerat neque parturient egestas. Proin pellentesque bibendum volutpat adipiscing sagittis habitant elit.
                        Odio justo dignissim ullamcorper purus ullamcorper sit semper dictum. Tortor est mauris aliquet amet sit ultrices auctor nulla. Faucibus aliquam etiam pharetra pellentesque sagittis odio lacus. Eu morbi senectus in massa fermentum elit in. Tincidunt est blandit malesuada auctor. Orci tellus mus aliquam accumsan ac. Et urna nisl facilisis non volutpat et sodales.
                        Malesuada egestas enim purus cras diam eget vel. Massa ante sit scelerisque scelerisque hac. Consequat tempor non pretium convallis. Interdum iaculis sit interdum interdum magna. Gravida urna et cursus donec consectetur nulla. Aliquet egestas nulla arcu aliquam facilisi duis maecenas viverra. Egestas consectetur mauris orci sit. Bibendum orci at viverra pharetra tortor nulla amet erat vehicula. Mauris volutpat amet in sit rhoncus. Imperdiet feugiat id fames gravida.
                    """.trimIndent()

    private val eventList = listOf(
        Event(
            id = UUID.randomUUID()
                .toString(),
            communityId = UUID.randomUUID()
                .toString(),
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
            communityId = UUID.randomUUID()
                .toString(),
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
            communityId = UUID.randomUUID()
                .toString(),
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
            communityId = UUID.randomUUID()
                .toString(),
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

}