const baseUrl = `${process.env.REACT_APP_API_URL}`;
const shorteningServerDomain = `${process.env.REACT_APP_SREVR_DOMAIN}`;

const getBaseUrl = () => {
    return baseUrl;
}

const getServerUrl = () => {
    return shorteningServerDomain;
}

export {getBaseUrl, getServerUrl};