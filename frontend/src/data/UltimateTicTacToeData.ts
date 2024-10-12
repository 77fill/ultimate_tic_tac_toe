import Validator from "../util/Validator"
import TicTacToeData from "./TicTacToeData"

export default class UltimateTicTacToeData {
	private validator = Validator.instance
	private raw: TicTacToeData[][]
		
	constructor(
		raw?: TicTacToeData[][]
	) {
		if(raw) {
			this.validator.isThreeByThree(raw)
			this.raw = raw
		}
		else {
			this.raw = []
			for(let x of [0,1,2]) {
				this.raw.push([])
				for(let y of [0,1,2])
					this.raw[x].push(new TicTacToeData())
			}
					
		}
	}
	
	public get(x: number, y: number) {
		this.validator.areCoordinates(x,y)
		
		return this.raw[x][y]
	}

	public set(x: number, y: number, value: TicTacToeData): UltimateTicTacToeData {
		const newRaw = []

		for(let newRawX of [0,1,2]) {
			newRaw.push(this.raw[newRawX])
		}

		let newRow = []
		for(let newRowY of [0,1,2]) {
			if(newRowY === y)
				newRow.push(value)
			else
				newRow.push(this.raw[x][newRowY])
		}
		newRaw[x] = newRow
		
		return new UltimateTicTacToeData(newRaw)
	}
}