import { RegularCellValue } from "../data/RegularCellValue"

type Props = {
    value: RegularCellValue
    set: (value: RegularCellValue) => void
}

export default function Cell(props: Props) {
    const onClick = (e: React.MouseEvent<HTMLSpanElement>) => {
        if(props.value === "")
            props.set("X")
    }

    return <span onClick={onClick}>{props.value}</span>
}