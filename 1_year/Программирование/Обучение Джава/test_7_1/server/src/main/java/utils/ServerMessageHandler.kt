package utils

class ServerMessageHandler : KoinComponent {
    private val queryPool = ArrayBlockingQueue<Pair<ArrayList<Packet>, SocketAddress>>(10000)
    private val outPool = ArrayBlockingQueue<Pair<ArrayList<Packet>, SocketAddress>>(10000)
    private val serializator = Serializator()
    private val logger = Logger.getLogger("Handler logger")
    private val socket = DatagramSocket(1488)
    private val clients: HashMap<String, ClientAssistant> by inject()

    init {
        val unlogged_client = "unlogged_user"
        clients[unlogged_client] = ClientAssistant(-32132)
    }

    fun executeQuery() {
        while (true) {
            if (queryPool.isNotEmpty()) {
                Thread().start().run {
                    val query = queryPool.first()
                    queryPool.remove(query)
                    val query_from = query.first.first().token
                    if (!clients.contains(query_from)) return
                    val out = clients[query_from]!!.executeQuery(query.first)
                    outPool.add(out to query.second)
                }
            }
        }
    }

    fun receiveMessage() {
        while (true) {
            Executors.newCachedThreadPool().run {
                val byteArray = ByteArray(65535)
                val datagramPacket = DatagramPacket(byteArray, byteArray.size)
                socket.receive(datagramPacket)
                val address = datagramPacket.socketAddress
                val query = unpackMessage(datagramPacket)
                val query_from = query.first().token
                logger.info("Message from client $query_from")
                queryPool.add(query to address)
            }
        }
    }
    fun sendMessage() {
        while (true) {
            if (outPool.isNotEmpty()) {
                ForkJoinPool.commonPool().run {
                    val out = outPool.first()
                    outPool.remove(out)
                    logger.info("Sending answer to ${out.first.first().token}")
                    val datagramPacket = packMessage(out.first, out.second)
                    socket.send(datagramPacket)
                }
            }
        }
    }

    private fun unpackMessage(datagramPacket: DatagramPacket) : ArrayList<Packet> {
        return deserializeMessage(String(datagramPacket.data, 0, datagramPacket.length))
    }

    private fun packMessage(packets: ArrayList<Packet>, address: SocketAddress): DatagramPacket {
        var byteArray = ByteArray(65535)
        val datagramPacket = DatagramPacket(byteArray, byteArray.size)
        byteArray = serializeMessage(packets).toByteArray()
        datagramPacket.setData(byteArray, 0, byteArray.size)
        datagramPacket.socketAddress = address
        return datagramPacket
    }

    private fun serializeMessage(packets: ArrayList<Packet>) : String {
        return serializator.serialize(packets)
    }

    private fun deserializeMessage(message: String) : ArrayList<Packet> {
        return serializator.deserialize(message, ArrayList<Packet>())
    }
}