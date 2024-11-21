package clientCommands

val help = ClientCommand("help", CommandType.VISIBILITY_ARG, Visibility.ALL_USERS, argToVisibility) {
        _, currentVisibilityLevel ->
    val commands = clientCommandInvoker.getCommands()
    printToClientPacket(
        if (commands.isEmpty()) "No commands"
        else {
            var out = "You can use this commands:\n"
            commands.toSortedMap().filter{ it.value.visibility == Visibility.ALL_USERS || it.value.visibility == currentVisibilityLevel }.forEach { out += it.key + "\n" }
            out.dropLast(1)
        }
    )
}