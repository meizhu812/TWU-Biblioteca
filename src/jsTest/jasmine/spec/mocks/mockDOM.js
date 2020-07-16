let dictionary = {};
let callBacks = {};
export let modalMessage;

export function setValues(dict) {
    dictionary = dict;
}

export function getValue(id) {
    return  dictionary[id];
}

export function onClick(id, callBack) {
    callBacks[id] = callBack;
}

export function hideModal() {
}

export function showModal(message = "") {
    modalMessage = message;
}

export function getClickCallback(id) {
    return callBacks[id];
}

export async function click(id) {
    await callBacks[id]();
}

export function reset() {
    dictionary = {};
    callBacks = {};
    modalMessage = null;
}