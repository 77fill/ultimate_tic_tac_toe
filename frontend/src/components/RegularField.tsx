import { styled } from "@mui/material"
import { RegularCellValue } from "../data/RegularCellValue"
import TicTacToeData from "../data/TicTacToeData"
import Cell from "./Cell"
import TabularField from "./TabularField"

type Props = {
	data: TicTacToeData
	set: (x:number,y:number) => void
	focused: boolean
	victory?: "X"|"O"
}

const StyledSpan = styled("span")(() => ({
	position: "absolute",
	top: "45px",
	left: "25px",
	fontSize: "80px",
}))

export default function RegularField(props: Props) {
	const get = (x:number,y:number)=>({
		value: props.data.get(x,y),
		set: () => props.set(x,y)
	})

	const victorySymbol = <StyledSpan>{props.victory}</StyledSpan>

	const tabularField = <TabularField focused={props.focused}>
		<Cell {...get(0,0)}/><Cell {...get(1,0)}/><Cell {...get(2,0)}/>
		<Cell {...get(0,1)}/><Cell {...get(1,1)}/><Cell {...get(2,1)}/>
		<Cell {...get(0,2)}/><Cell {...get(1,2)}/><Cell {...get(2,2)}/>
	</TabularField>
	
	if(props.victory)
		return <>
			{tabularField}{victorySymbol}
		</>
	else
		return tabularField
}