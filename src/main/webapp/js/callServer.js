import * as authorization from './authorization.js';

export class Request {
    constructor(type, url, data) {
        this.type = type;
        this.url = url;
        this.data = data;
    }
}

export const requestType = {
    GET: 'GET',
    POST: 'POST'
};

export function call(request, auth = authorization) {
    return fetch(request.url, {
       method: request.type,
       headers: {
           "Content-type": "application/json",
           "Authorization": auth.getAuthorizationToken()
       },
       body: JSON.stringify(request.data)
    })
        .then(status)
        .then(json)
        .then(parsedData)
        .catch(error)
}

function status(response) {
    if (response.status !== 200) {
        return Promise.reject(new Error(response.statusText))
    }
    return Promise.resolve(response)
}

function json(response) {
    return response.json()
}

function parsedData(json) {
    return Promise.resolve(JSON.parse(json))
}

function error(error) {
    return Promise.reject(error)
}
