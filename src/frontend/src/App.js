import './App.css'
import Header from './Components/Header';
import person from "./Resources/gray-person.png";

function App() {
  return (
    <Header user={{id: 1337, logo: person, username: 'pidor', description: 'What does the fox say?'}}/>
  );
}

export default App;
