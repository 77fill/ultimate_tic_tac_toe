import { styled } from "@mui/material"
import { RegularCellValue } from "../data/RegularCellValue"

type Props = {
    value: RegularCellValue
    set: () => void
}

const StyledSpan = styled("span")(() => ({
    display: "inline-block",
    width: "30px",
    height: "30px"
}))

export default function Cell(props: Props) {
    const onClick = (e: React.MouseEvent<HTMLSpanElement>) => {
        if(props.value === "")
            props.set()
    }

    return <StyledSpan onClick={onClick}>{props.value}</StyledSpan>
}