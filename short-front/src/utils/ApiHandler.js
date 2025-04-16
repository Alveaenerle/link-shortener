import { getBaseUrl } from "./EnvInfo";
const baseUrl = getBaseUrl();

const post_plain = async (url, body) => {
    try {
        const response = await fetch(`${baseUrl}${url}`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: body,
        })

        return response;
    } catch (error) {
        // TODO redirect to error page
        console.error('Error:', error);
    }
}

const get_plain = async (url) => {
    try {
        const response = await fetch(`${baseUrl}${url}`, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
            }
        })

        return response;
    } catch (error) {
        // TODO redirect to error page
        console.error('Error:', error);
    }
}

const get_base_url = () => {
    return baseUrl;
}

export { post_plain, get_plain, get_base_url };