import './App.css'
import person from "./Resources/gray-person.png";

function App() {
  return (
    <div className="Header">
      <div className="User-logo">
        <img src={person} alt="" width="100px" height="100px"/>
      </div>
    </div>
  );
}

export default App;
