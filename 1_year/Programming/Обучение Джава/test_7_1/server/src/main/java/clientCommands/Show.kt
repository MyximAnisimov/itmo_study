package clientCommands


val show = ClientCommand("show", CommandType.NO_ARG, Visibility.LOGGED_USER, {}) {
        user_id, _ ->
    val collection = collectionManager.collection
    printToClientPacket (
        if (collection.isEmpty()) "Collection is empty :("
        else {
            var out = "Collection:\n"
            collection.sortedBy { it.getName() }.forEach { out += it.toString() + "\n" }
            out.dropLast(1)
        }
    )
}