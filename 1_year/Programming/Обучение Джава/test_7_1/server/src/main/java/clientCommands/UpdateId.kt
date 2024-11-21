package clientCommands

val updateId = ClientCommand("update", CommandType.MIXED_ARG, Visibility.LOGGED_USER, argToLongAndRoute) {
        user_id, (id, route) ->
    printToClientPacket (
        try {
            if (dbHandler.updateElement(route, user_id)) {
                collectionManager.collection.first { it.getId() == id }.update(
                    name = route.getName(),
                    coordinates = route.getCoordinates(),
                    from = route.getFrom(),
                    to = route.getTo(),
                    distance = route.getDistance()
                )
                "Done"
            }
            else "You can't update this element"
        } catch (e: java.lang.Exception) {
            "Wrong id format."
        }
    )}