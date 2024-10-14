import { styled, Table, TableCell, TableRow } from "@mui/material"
import React from "react"

type Props = {
	
} & React.PropsWithChildren

const StyledTableCell = styled(TableCell)(()=>({
	padding: 0,
	borderWidth: "3px",
	borderStyle: "solid",
	borderColor: "#B1827A",
	width: "30px",
	height: "30px",
	textAlign: "center",
	fontSize: "1.7rem",
	lineHeight: "1.7rem"
}))

export default function TabularField(props: Props) {
	const children = React.Children.toArray(props.children)
	
	return <Table sx={{width: "initial", margin: "0 auto"}}>
		<TableRow>
			<StyledTableCell>{children[0]}</StyledTableCell>
			<StyledTableCell>{children[1]}</StyledTableCell>
			<StyledTableCell>{children[2]}</StyledTableCell>
		</TableRow>
		<TableRow>
			<StyledTableCell>{children[3]}</StyledTableCell>
			<StyledTableCell>{children[4]}</StyledTableCell>
			<StyledTableCell>{children[5]}</StyledTableCell>
		</TableRow>
		<TableRow>
			<StyledTableCell>{children[6]}</StyledTableCell>
			<StyledTableCell>{children[7]}</StyledTableCell>
			<StyledTableCell>{children[8]}</StyledTableCell>
		</TableRow>
	</Table>
}