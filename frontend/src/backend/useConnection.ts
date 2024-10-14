import { SetStateAction, useState } from "react";
import UltimateTicTacToeData from "../data/UltimateTicTacToeData";
import Message from "./Message";
import tttAdapt from "./tttAdapt";


export default function useConnection(setUltimateFieldData: React.Dispatch<SetStateAction<UltimateTicTacToeData>>) {
    const [ws, setWs] = useState(new WebSocket("ws://localhost:8080/websocket/player"))

    ws.onopen = e => {

    }

    ws.onmessage = e => {
        setUltimateFieldData(tttAdapt(e.data))
    }

    const send = async (msg: Message) => {
        ws.send(JSON.stringify(msg))
    }

    return send
}