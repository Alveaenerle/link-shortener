import React from "react";
import Header from "../components/Header";
import '../styles/Home.css';
import '../styles/Describe.css'
import { useState, useEffect } from "react";
import { post_plain, get_plain, get_base_url } from "../utils/ApiHandler";
import { getServerUrl } from "../utils/EnvInfo";

function Describe() {
  const [longUrl, setLongUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');

  const getLongUrl = async () => {
    const path = window.location.pathname;
    console.log('Path: ' + path);
    // Match URL of the form /describe/abc
    const match = path.match(/^\/describe\/([\w\-]+)$/);
    console.log("Match: " + match);
    if (!match) return [null, null];

    const slug = match[1];
    const response = await get_plain('/long/' + slug);
    if (!response.ok) return [null, null];

    const long = await response.text();
    if (!long) return [null, null];

    return [long, slug];
  };

  const setUrls = async () => {
    const [long, slug] = await getLongUrl();
    console.log(long + '\t\t' + slug);
    if (!long || !slug) return;

    setShortUrl(getServerUrl() + slug);
    setLongUrl(long);
  };

  useEffect(() => {
    const init = async () => {
      await setUrls();
    };
    init();
  }, []);

  return (
    <div className="home-background">
      <Header/>
      <div className="content">
        <div className="input-box">
          <text className="box-desc-text">
            Your Short Url
          </text>
          <div className="output-container-desc">
            <input
              className="url-output"
              type="text"
              readOnly
              value= {shortUrl}
            />
          </div>
          <text className="box-desc-text">
            URL that has been shortened
          </text>
          <div className="output-container-desc">
            <input
              className="url-output"
              type="text"
              readOnly
              value= {longUrl}
            />
          </div>

        </div>
      </div>
        
    </div>
  );

}

export default Describe ;