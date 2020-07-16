import setUpLogin from "../../../main/webapp/js/setUpLogin.js";
import * as mockDOM from "./mocks/mockDOM.js";
import * as mockAPI from "./mocks/mockCallServer.js"
import * as mockAuth from "./mocks/mockAuthorization.js"

describe("login", function () {

    let input;

    beforeEach(function () {
        spyOn(window, 'alert');

        mockDOM.reset();
        mockAPI.reset();
        mockAuth.reset();

        input = {
            libraryNumber: "12345",
            password: "12345"
        };

        mockDOM.setValues(input);
    });

    it('should set up login button action', function () {
        expect(mockDOM.getClickCallback("login")).toBeUndefined();

        setUpLogin(mockDOM, mockAPI, mockAuth);

        expect(mockDOM.getClickCallback("login")).toBeDefined();
    });

    it('should display success alert on success of login request', function (done) {
        setUpLogin(mockDOM, mockAPI, mockAuth);

        mockAPI.setRequestSuccessful(true);
        mockAPI.setResponse({
            message: "Login Successful",
            token: "abcd-efgh"
        });

        mockDOM.click("login")
            .then(() => {
                expect(alert).toHaveBeenCalledWith("Login Successful");
                expect(mockAuth.token).toEqual("abcd-efgh");

                const request = new mockAPI.Request("POST", "login", input);
                expect(mockAPI.request).toEqual(request);

                done();
             });
    });

    it('should display failure alert on failure of login request', function (done) {
        setUpLogin(mockDOM, mockAPI, mockAuth);

        mockAPI.setRequestSuccessful(false);
        mockAPI.setError("error");

        mockDOM.click("login")
            .then(() => {
                expect(alert).toHaveBeenCalledWith("error");
                done();
            });
    });
});