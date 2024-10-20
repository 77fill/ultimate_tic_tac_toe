import { SetStateAction, useState } from "react";
import UltimateTicTacToeData from "../data/UltimateTicTacToeData";
import Message from "./Message";
import tttAdapt from "./tttAdapt";
import Connector from "./Connector";
import { RegularCellValue } from "../data/RegularCellValue";


export default function useConnection(
    setUltimateFieldData: React.Dispatch<SetStateAction<UltimateTicTacToeData>>,
    setItsYourTurn: React.Dispatch<SetStateAction<boolean>>,
    setSymbol: React.Dispatch<SetStateAction<RegularCellValue>>,
    setCurrentFieldCoords: React.Dispatch<SetStateAction<[number,number]|[]>>,
    setVictories: React.Dispatch<SetStateAction<["X"|"O",number,number][]>>) {

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
                setCurrentFieldCoords(msg.currentFieldCoords)
                setItsYourTurn(true)
                break;
            case "setSymbol":
                setSymbol(msg.symbol)
                break;
            case "violation":
                setItsYourTurn(true)
                setUltimateFieldData(tttAdapt(msg.symbols))
                break;
            case "victory":
                if(msg.metaCoords.length === 2) {
                    setVictories(old => [...old, [msg.symbol, msg.metaCoords[0], msg.metaCoords[1]]])
                }
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