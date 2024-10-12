export default class Validator {
	public static instance: Validator = new Validator()
	
	public isCoordinate(coord:number) {
		if(!Number.isInteger(coord)
					|| coord < 0 || coord > 2) {
						throw new Error("Coordinate "+ coord +" is not valid!")
					}
	}
	
	public areCoordinates(...coords: number[]) {
		coords.forEach(coord => this.isCoordinate(coord))
	}
}