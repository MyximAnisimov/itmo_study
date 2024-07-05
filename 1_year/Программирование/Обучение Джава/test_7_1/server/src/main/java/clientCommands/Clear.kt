package clientCommands

val clear = ClientCommand("clear", CommandType.NO_ARG, Visibility.LOGGED_USER, {}) {
        user_id, _ ->
    if (dbHandler.clearCollection(user_id)) { collectionManager.collection.removeIf { it.getOwner() == user_id } }
    printToClientPacket("Removed all your Routes")
}