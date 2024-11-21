package clientCommands

import builders.*
import commandArgumentsAndTheirsComponents.CommandType
import commandArgumentsAndTheirsComponents.Visibility
import utils.argToRoute

val add = ClientCommand("add", CommandType.OBJECT_ARG, Visibility.LOGGED_USER, argToRoute) {
        user_id, objectArg ->
    val route_id = dbHandler.addElement(objectArg, user_id)
    if (route_id > -1) {
        objectArg.setId(route_id)
        objectArg.setOwner(user_id)
        collectionManager.collection.add(objectArg)
        printToClientPacket("Done")
    } else {
        printToClientPacket("Something went wrong")
    }
}