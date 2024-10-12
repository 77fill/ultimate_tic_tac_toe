import Validator from "../util/Validator"
import {RegularCellValue} from "../data/RegularCellValue"

export default class TicTacToeData {
	private validator = Validator.instance
	private raw: RegularCellValue[][] 
	
	constructor();
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
		
		return this.raw[x][y]
	}
	
}