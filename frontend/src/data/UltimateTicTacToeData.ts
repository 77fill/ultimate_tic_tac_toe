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
		
		return this.raw[y][x]
	}

	public set(x: number, y: number, value: TicTacToeData): UltimateTicTacToeData {
		const newRaw = []

		for(let newRawY of [0,1,2]) {
			newRaw.push(this.raw[newRawY])
		}

		let newRow = []
		for(let newCellX of [0,1,2]) {
			if(newCellX === x)
				newRow.push(value)
			else
				newRow.push(this.raw[y][newCellX])
		}
		newRaw[y] = newRow
		
		return new UltimateTicTacToeData(newRaw)
	}
}