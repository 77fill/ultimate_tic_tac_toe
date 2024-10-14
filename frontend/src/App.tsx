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
	const send = useConnection(setUltimateFieldData)

	const get = (metaX:number,metaY:number) => ({
		data: ultimateFieldData.get(metaX,metaY),
		set: (x:number,y:number) => {
			send(new Message("coords",metaX, metaY,x,y))

		 	setUltimateFieldData( 
				old => old.set(
					metaX,metaY,
					old.get(metaX,metaY)
						.set(x,y,"X")) )
		}
	})

  return (
    <div className="App">
      <main>
	  	<TabularField>
			<Field {...get(0,0)}/><Field {...get(1,0)}/><Field {...get(2,0)}/>
			<Field {...get(0,1)}/><Field {...get(1,1)}/><Field {...get(2,1)}/>
			<Field {...get(0,2)}/><Field {...get(1,2)}/><Field {...get(2,2)}/>
		</TabularField>
	  </main>
    </div>
  );
}

export default App;
