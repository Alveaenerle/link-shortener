import React from "react"
import '../styles/Header.css';

function Header() {
  return (
    <header className="fixed-header">
      <div className="logo">YapYap</div>
      <div className="header-title">URL Shortener</div>
    </header>
  );
}

export default Header;