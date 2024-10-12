import { RegularCellValue } from "../data/RegularCellValue"

type Props = {
    value: RegularCellValue
    set: () => void
}

export default function Cell(props: Props) {
    const onClick = (e: React.MouseEvent<HTMLSpanElement>) => {
        if(props.value === "")
            props.set()
    }

    return <span onClick={onClick}>{props.value}</span>
}