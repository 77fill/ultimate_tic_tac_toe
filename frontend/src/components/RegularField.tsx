import TicTacToeData from "../data/TicTacToeData"
import TabularField from "./TabularField"

type Props = {
	data: TicTacToeData
}

export default function RegularField(props: Props) {
	const get = (x:number,y:number)=>props.data.get(x,y)

	return <TabularField>
		<>{get(0,0)}</><>{get(1,0)}</><>{get(2,0)}</>
		<>{get(0,1)}</><>{get(1,1)}</><>{get(2,1)}</>
		<>{get(0,2)}</><>{get(1,2)}</><>{get(2,2)}</>
	</TabularField>
}