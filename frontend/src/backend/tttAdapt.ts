import { RegularCellValue } from "../data/RegularCellValue";
import TicTacToeData from "../data/TicTacToeData";
import UltimateTicTacToeData from "../data/UltimateTicTacToeData";

export default function tttAdapt(obj: any): UltimateTicTacToeData {
    const three = [0,1,2]

    const metaRaw = [] as TicTacToeData[][]
    for(let metaY of three) {
        const metaRow = [] as TicTacToeData[]
        for(let metaX of three) {
            const raw = [] as RegularCellValue[][]
            for(let y of three) {
                const row = [] as RegularCellValue[]
                for(let x of three) {
                    row.push(obj[x + 3*y + 9*metaX + 27*metaY])
                }
                raw.push(row)
            }
            metaRow.push(new TicTacToeData(raw))
        }
        metaRaw.push(metaRow)
    }
    return new UltimateTicTacToeData(metaRaw)       
}