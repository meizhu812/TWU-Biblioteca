export function getValue(id) {
    return document.getElementById(id).value;
}

export function onClick(id, callback) {
    document.getElementById(id).onclick = callback;
}

export function hideModal() {
    document.getElementById("modal").classList.add("modal-hide");
}

export function showModal(message = "") {
    document.getElementById("modal-message").innerText = message;
    document.getElementById("modal").classList.remove("modal-hide");
}