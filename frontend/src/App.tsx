import React, { useState } from 'react';
import './App.css';
import TabularField from './components/TabularField';
import Field from './components/RegularField';
import UltimateTicTacToeData from "./data/UltimateTicTacToeData"

function App() {
	const [ultimateFieldData, setUltimateFieldData] = useState(new UltimateTicTacToeData())
	const get = (x:number,y:number) => ({
		data: ultimateFieldData.get(x,y)
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
