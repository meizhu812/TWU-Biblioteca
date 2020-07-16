export default function setUpCheckoutBook(dom, api) {
    const getInput = () => {
        return {bookName : dom.getValue("bookName")}
    };

    async function checkoutBooks() {
        const request = new api.Request(api.requestType.POST, "checkoutBook", getInput());

        const success = (response) => {
            dom.showModal(response.message);
        };

        const failure = (error) => {
            dom.showModal(error);
        };

        await api.call(request)
            .then(success)
            .catch(failure);
    }

    dom.onClick("checkout", checkoutBooks);
    dom.onClick("modal-close", dom.hideModal);
    dom.onClick("modal-shader", dom.hideModal);
};