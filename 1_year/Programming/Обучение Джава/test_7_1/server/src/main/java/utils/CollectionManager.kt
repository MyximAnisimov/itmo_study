package utils

class CollectionManager: KoinComponent {
    @Serializable
    var collection: MutableCollection<Route> = LinkedBlockingDeque()
    private var supportedCollectionTypes: HashMap<String, MutableCollection<Route>> = hashMapOf()
    private val dbHandler: DBHandler by inject()

    init {
        addSupportedCollectionType("linkedblockingqueue", LinkedBlockingQueue())
        addSupportedCollectionType("linkedblockingdeque", LinkedBlockingDeque())

        val type = supportedCollectionTypes.filter { it.value::class == collection::class }.keys.first()
        collection = dbHandler.downloadCurrentCollection()
        changeType(type)
    }
    fun changeType(newType: String) {
        if (collection == getSupportedCollectionTypes()[newType]!!) return
        val old = collection
        collection = supportedCollectionTypes[newType.lowercase()]!!
        for (element in old) {
            collection.add(element)
        }
    }
    fun addSupportedCollectionType(name: String, collection: MutableCollection<Route>) {
        supportedCollectionTypes[name] = collection
    }
    fun getSupportedCollectionTypes() = supportedCollectionTypes

}