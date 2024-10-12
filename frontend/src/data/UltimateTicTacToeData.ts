import Validator from "../util/Validator"
import TicTacToeData from "./TicTacToeData"

export default class UltimateTicTacToeData {
	private validator = Validator.instance
	private raw: TicTacToeData[][]
	
	constructor();	
	constructor(
		raw?: TicTacToeData[][]
	) {
		if(raw)
			this.raw = raw
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
}