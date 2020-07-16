export let token;

export function setAuthorizationToken(tk) {
    token = tk;
}

export function reset() {
    token = null;
}