import logo from './logo.svg';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Describe from './pages/Describe';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/describe/*" element={<Describe />} />
        <Route path="*" element={<h1> Page not found! 404</h1>} />
      </Routes>
    </Router>
  );
}

export default App;
