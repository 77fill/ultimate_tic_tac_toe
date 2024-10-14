export default class Connector {
    private static ws?: WebSocket = undefined

    public static getWs() {
        if(!Connector.ws)
            Connector.ws = new WebSocket("ws://localhost:8080/websocket/player")

        return Connector.ws
    }
}