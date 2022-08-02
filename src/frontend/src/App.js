import './App.css'
import { BrowserRouter, Routes, Route  } from 'react-router-dom';
import Header from './Components/Header';
import person from "./Resources/gray-person.png";
import NoPage from './Components/NoPage';
import Authorization from './Components/Authorization';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Authorization/>}/>
        <Route path="/main" element={
          <Header user={{id: 1337, logo: person, username: 'sidor', description: 'What does the fox say?'}}/>}
        />        
        <Route path="*" element={<NoPage/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
