export let request;
let requestSuccessful;
let response;
let error;

export class Request {
        constructor(type, url, data) {
            this.type = type;
            this.url = url;
            this.data = data;
        }
}

export const requestType = {
     POST: "POST"
};

export function call(req) {
    request = req;
    if(requestSuccessful) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(error)
    }
}

export function setRequestSuccessful(successful) {
    requestSuccessful = successful;
}

export function setResponse(res) {
    response = res;
}

export function setError(err) {
    error = err;
}

export function reset() {
    request = null;
    requestSuccessful = null;
    response = null;
    error = null;
}

