import '../styles/Home.css';
import React, { useState } from 'react';

function Home() {
  const [inputUrl, setInputUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');

  const handleShorten = () => {
    // Simulate a shortened URL (replace with your API as needed)
    const fakeShort = 'https://sho.rt/' + Math.random().toString(36).substr(2, 5);
    setShortUrl(fakeShort);
  };

  const handleCopy = async () => {
    if (shortUrl) {
      await navigator.clipboard.writeText(shortUrl);
      alert('Copied to clipboard!');
    }
  };

  return (
    <div className="home-background">
      <header className="fixed-header">
        <div className="logo">YapYap</div>
        <div className="header-title">URL Shortener</div>
      </header>
      
      {/* The main content is pushed down to avoid overlapping the fixed header */}
      <div className="content">
        <div className="input-box">
          <div className="input-row">
            <input
              className="url-input"
              type="text"
              placeholder="Paste your URL here"
              value={inputUrl}
              onChange={(e) => setInputUrl(e.target.value)}
            />
            <button className="shorten-btn" onClick={handleShorten}>
              Shorten URL
            </button>
          </div>
          {shortUrl && (
            <div className="output-container">
              <input
                className="url-output"
                type="text"
                readOnly
                value={shortUrl}
              />
              <button className="copy-btn" onClick={handleCopy}>
                Copy
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Home;
