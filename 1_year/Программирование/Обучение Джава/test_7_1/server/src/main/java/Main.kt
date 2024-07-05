import org.koin.core.component.inject
import org.koin.core.context.startKoin
import utils.*
import kotlin.concurrent.thread
fun main(args: Array<String>)  {
    startKoin {
        modules(dbHandlerModule, serverKoinModule)
    }
    val serverUtilFabric = ServerUtilFabric()
    val serverMessageHandler: ServerMessageHandler by serverUtilFabric.inject()
    thread { serverMessageHandler.receiveMessage() }
    thread { serverMessageHandler.executeQuery() }
    thread { serverMessageHandler.sendMessage() }
}