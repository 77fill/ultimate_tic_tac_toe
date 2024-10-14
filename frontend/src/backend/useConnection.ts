import { SetStateAction, useState } from "react";
import UltimateTicTacToeData from "../data/UltimateTicTacToeData";
import Message from "./Message";
import tttAdapt from "./tttAdapt";
import Connector from "./Connector";


export default function useConnection(setUltimateFieldData: React.Dispatch<SetStateAction<UltimateTicTacToeData>>) {
    const [ws, setWs] = useState(Connector.getWs)

    ws.onopen = e => {

    }

    ws.onmessage = e => {
        const msg = JSON.parse(e.data)

        console.log("onmessage", msg.symbols)

        if(msg.type === "gameState")
            setUltimateFieldData(tttAdapt(msg.symbols))
    }

    ws.onclose = e => {
        console.log("onclose!")
    }

    const send = async (msg: Message) => {
        ws.send(JSON.stringify(msg))
    }

    return send
}