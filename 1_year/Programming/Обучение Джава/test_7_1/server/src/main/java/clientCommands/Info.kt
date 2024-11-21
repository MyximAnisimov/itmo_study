package clientCommands

val info = ClientCommand("info", CommandType.NO_ARG, Visibility.LOGGED_USER, {}) {
        _, _ ->
    printToClientPacket(
        "Information about collection:" +
                "\n\tType: ${collectionManager.collection.javaClass.simpleName}" +
                "\n\tSize: ${collectionManager.collection.size}" +
                "\nInfo about system:" +
                "\n\tSerialization strategy: ${serializator.getChosenStrategy().toString()}"
    )
}