import { RegularCellValue } from "../data/RegularCellValue"
import TicTacToeData from "../data/TicTacToeData"
import Cell from "./Cell"
import TabularField from "./TabularField"

type Props = {
	data: TicTacToeData
	set: (x:number,y:number) => void
	focused: boolean
}

export default function RegularField(props: Props) {
	const get = (x:number,y:number)=>({
		value: props.data.get(x,y),
		set: () => props.set(x,y)
	})
	
	return <TabularField focused={props.focused}>
		<Cell {...get(0,0)}/><Cell {...get(1,0)}/><Cell {...get(2,0)}/>
		<Cell {...get(0,1)}/><Cell {...get(1,1)}/><Cell {...get(2,1)}/>
		<Cell {...get(0,2)}/><Cell {...get(1,2)}/><Cell {...get(2,2)}/>
	</TabularField>
}