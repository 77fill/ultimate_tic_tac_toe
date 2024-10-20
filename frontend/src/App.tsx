import React, { useEffect, useState } from 'react';
import './App.css';
import TabularField from './components/TabularField';
import Field from './components/RegularField';
import UltimateTicTacToeData from "./data/UltimateTicTacToeData"
import TicTacToeData from './data/TicTacToeData';
import { RegularCellValue } from './data/RegularCellValue';
import useConnection from './backend/useConnection';
import Message from './backend/Message';

function App() {
	const [ultimateFieldData, setUltimateFieldData] = useState(new UltimateTicTacToeData())
	const [itsYourTurn, setItsYourTurn] = useState(false)
	const [currentFieldCoords, setCurrentFieldCoords] = useState([] as [number,number]|[])
	const [symbol, setSymbol] = useState("X" as RegularCellValue)
	const [victories, setVictories] = useState([] as ["X"|"O",number,number][])

	const send = useConnection(
		setUltimateFieldData, 
		setItsYourTurn, 
		setSymbol,
		setCurrentFieldCoords,
		setVictories)

	const get = (metaX:number,metaY:number) => ({
		focused: currentFieldCoords[0] === metaX && currentFieldCoords[1] === metaY,
		victory: victories.find(entry => entry[1] === metaX && entry[2] === metaY)?.[0],
		data: ultimateFieldData.get(metaX,metaY),
		set: (x:number,y:number) => {
			if(!itsYourTurn)
				return;
			if(ultimateFieldData.get(metaX,metaY).get(x,y) !== "")
				return;

			send(new Message("coords",metaX, metaY,x,y))

		 	setUltimateFieldData( 
				old => old.set(
					metaX,metaY,
					old.get(metaX,metaY)
						.set(x,y,symbol)) )

			setItsYourTurn(false)
		}
	})

  return (
    <div className="App">
      <main>
	  	<TabularField focused={currentFieldCoords.length === 0}>
			<Field {...get(0,0)}/><Field {...get(1,0)}/><Field {...get(2,0)}/>
			<Field {...get(0,1)}/><Field {...get(1,1)}/><Field {...get(2,1)}/>
			<Field {...get(0,2)}/><Field {...get(1,2)}/><Field {...get(2,2)}/>
		</TabularField>
	  </main>
    </div>
  );
}

export default App;
