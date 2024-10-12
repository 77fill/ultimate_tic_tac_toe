import { Table, TableCell, TableRow } from "@mui/material"
import React from "react"

type Props = {
	
} & React.PropsWithChildren

export default function TabularField(props: Props) {
	const children = React.Children.toArray(props.children)
	
	return <Table>
		<TableRow>
			<TableCell>{children[0]}</TableCell>
			<TableCell>{children[1]}</TableCell>
			<TableCell>{children[2]}</TableCell>
		</TableRow>
		<TableRow>
			<TableCell>{children[3]}</TableCell>
			<TableCell>{children[4]}</TableCell>
			<TableCell>{children[5]}</TableCell>
		</TableRow>
		<TableRow>
			<TableCell>{children[6]}</TableCell>
			<TableCell>{children[7]}</TableCell>
			<TableCell>{children[8]}</TableCell>
		</TableRow>
	</Table>
}