export default function setUpLogin(dom, api, auth) {

    const getInput = () => {
        return {
            libraryNumber : dom.getValue("libraryNumber"),
            password: dom.getValue("password")
        }
    };

    async function login() {
        const request = new api.Request(api.requestType.POST, "login", getInput(), null);

        const success = (response) => {
            auth.setAuthorizationToken(response.token);
            window.alert(response.message);
        };

        const failure = (error) => {
            window.alert(error);
        };

        await api.call(request)
            .then(success)
            .catch(failure);
    }

    dom.onClick("login", login);
}