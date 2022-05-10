import './style.css';
import { React } from "react";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Dashboard from './Dashboard';

//Includes Browser Router for future expansion of App's Capabilities

function App() {
  return (
    <div className="wrapper">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Dashboard />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
