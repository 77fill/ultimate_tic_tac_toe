import Validator from "../util/Validator"
import {RegularCellValue} from "../data/RegularCellValue"

export default class TicTacToeData {
	private validator = Validator.instance
	private raw: RegularCellValue[][] 
	
	constructor(
		raw?: RegularCellValue[][]
	) {
		if(raw)
			this.raw = raw
		else {
			this.raw = []
			for(let x of [0,1,2]) {
				this.raw.push([])
				for(let y of [0,1,2])
					this.raw[x].push("")
			}
					
		}
	}
	
	public get(x: number, y: number) {
		this.validator.areCoordinates(x,y)
		
		return this.raw[y][x]
	}
	
	public set(x: number, y: number, value: RegularCellValue): TicTacToeData {
		const newRaw = [] as RegularCellValue[][]

		for(let newRawY of [0,1,2]) {
			newRaw.push(this.raw[newRawY])
		}

		let newRow = [] as RegularCellValue[]
		for(let newCellX of [0,1,2]) {
			if(newCellX === x)
				newRow.push(value)
			else
				newRow.push(this.raw[y][newCellX])
		}
		newRaw[y] = newRow
		
		return new TicTacToeData(newRaw)
	}
}