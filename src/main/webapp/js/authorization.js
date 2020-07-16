const tokenKey = "Token";

export function setAuthorizationToken(tokenValue) {
    localStorage.setItem(tokenKey, tokenValue);
}

export function getAuthorizationToken() {
   return localStorage.getItem(tokenKey);
}