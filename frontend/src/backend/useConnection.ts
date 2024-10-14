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

        if(e.data.type === "gameState")
            setUltimateFieldData(tttAdapt(e.data))
    }

    const send = async (msg: Message) => {
        ws.send(JSON.stringify(msg))
    }

    return send
}