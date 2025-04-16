import '../styles/Home.css';
import React, { useState } from 'react';
import { post_plain, get_plain } from '../utils/ApiHandler';
import Header from '../components/Header';
import { getServerUrl } from '../utils/EnvInfo';


function Home() {
  const shorteningServerDomain = getServerUrl();
  const [inputUrl, setInputUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');

  const handleShorten = async () => {
    const response = await post_plain('/create', inputUrl);
    if (!response.ok) {
      return;
      // TODO inform the user 
    }
    const text = await response.text();
    if (text == null || text === '') {
      return;
    }
    setShortUrl(shorteningServerDomain + text);
  };

  const handleCopy = async () => {
    if (shortUrl) {
      await navigator.clipboard.writeText(shortUrl);
      alert('Copied to clipboard!');
    }
  };

  return (
    <div className="home-background">
      <Header/>
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
