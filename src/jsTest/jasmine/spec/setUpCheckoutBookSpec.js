import setUpCheckoutBook from "../../../main/webapp/js/setUpCheckoutBook.js";
import * as mockDOM from "./mocks/mockDOM.js";
import * as mockAPI from "./mocks/mockCallServer.js"
import * as mockAuth from "./mocks/mockAuthorization.js"

describe("checkoutBook", function () {

    let input;

    beforeEach(function () {
        mockDOM.reset();
        mockAPI.reset();
        mockAuth.reset();

        input = {
            bookName: "abcd"
        };

        mockDOM.setValues(input);
    });

    it('should set up checkout button action', function () {
        expect(mockDOM.getClickCallback("checkout")).toBeUndefined();

        setUpCheckoutBook(mockDOM, mockAPI, mockAuth);

        expect(mockDOM.getClickCallback("checkout")).toBeDefined();
    });

    it('should display success modal on success of request', function (done) {
        setUpCheckoutBook(mockDOM, mockAPI, mockAuth);

        mockAPI.setRequestSuccessful(true);
        mockAPI.setResponse({
            message: "Thank you! Enjoy your book."
        });

        mockDOM.click("checkout")
            .then(() => {
                expect(mockDOM.modalMessage).toEqual("Thank you! Enjoy your book.");

                const request = new mockAPI.Request("POST", "checkoutBook", input);
                expect(mockAPI.request).toEqual(request);

                done();
            });
    });

    it('should display failure modal on failure of request', function (done) {
        setUpCheckoutBook(mockDOM, mockAPI, mockAuth);

        mockAPI.setRequestSuccessful(false);
        mockAPI.setError("error");

        mockDOM.click("checkout")
            .then(() => {
                expect(mockDOM.modalMessage).toEqual("error");
                done();
            });
    });
});