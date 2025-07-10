import React from "react";
import './App.css';
import Header from './Header';
import Nav from "./Nav";
import MainContent from "./MainContent";
import Footer from "./Footer";
function App() {
  return (
      <div className="App-layout-container">
        <Header />
        <Nav />
        <MainContent />
        <Footer />
      </div>
  );
}

export default App;