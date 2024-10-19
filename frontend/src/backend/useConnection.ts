import { SetStateAction, useState } from "react";
import UltimateTicTacToeData from "../data/UltimateTicTacToeData";
import Message from "./Message";
import tttAdapt from "./tttAdapt";
import Connector from "./Connector";
import { RegularCellValue } from "../data/RegularCellValue";


export default function useConnection(
    setUltimateFieldData: React.Dispatch<SetStateAction<UltimateTicTacToeData>>,
    setItsYourTurn: React.Dispatch<SetStateAction<boolean>>,
    setSymbol: React.Dispatch<SetStateAction<RegularCellValue>>) {

    const [ws, setWs] = useState(Connector.getWs)

    ws.onopen = e => {

    }

    ws.onmessage = e => {
        const msg = JSON.parse(e.data)

        console.log("onmessage", msg.type)

        switch(msg.type) {
            case "gameState":
                setUltimateFieldData(tttAdapt(msg.symbols))
                setItsYourTurn(false)
                break;
            case "itsYourTurn":
                setItsYourTurn(true)
                break;
            case "setSymbol":
                setSymbol(msg.symbol)
                break;
        }
    }

    ws.onclose = e => {
        console.log("onclose!")
    }

    const send = async (msg: Message) => {
        ws.send(JSON.stringify(msg))
    }

    return send
}