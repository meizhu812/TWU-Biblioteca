import  * as api from "../../../main/webapp/js/callServer.js";

describe("call server", function () {

    let mockAuthorization;

    beforeEach(function () {
        mockAuthorization = {
            getAuthorizationToken: () => { return "abcd-efgh"}
        }
    });

    it('should prepare a post request and return the response', function (done) {
        const requestBody = {
            userName: "abcd",
            password: "abcd"
        };

        const request = new api.Request(api.requestType.POST, "hello", requestBody);

        const mockResponse = {
            status: 200,
            json: () => Promise.resolve('{"message": "Login Successful"}')
        };

        spyOn(window, 'fetch').and.returnValue(Promise.resolve(mockResponse));

        api.call(request, mockAuthorization)
            .then(success => {
                assertPostRequest(success);
                done()
            })
            .catch(error => {
                done.fail(error);
            });

        function assertPostRequest(response) {
            const requestInit = {
                method: 'POST',
                headers: {
                    "Content-type": "application/json",
                    "Authorization": "abcd-efgh" },
                body: JSON.stringify(requestBody)
            };

            const expectedResponse = {
                message: "Login Successful"
            };

            expect(fetch).toHaveBeenCalledWith("hello", requestInit);
            expect(response).toEqual(expectedResponse);
        }

        expect(window.fetch).toHaveBeenCalled();
    });


    it('should return an error if call fails', function (done) {
        const request = new api.Request(api.requestType.GET, "hello", null);

        const error = {
            message: "Call failed"
        };

        spyOn(window, 'fetch').and.returnValue(Promise.reject(error));

        api.call(request, mockAuthorization)
            .then(success => {})
            .catch(error => {
                expect(error).toEqual(error)
                done();
            });

        expect(window.fetch).toHaveBeenCalled();
    });

    it('should return an error if response status is not 200', function (done) {
        const request = new api.Request(api.requestType.GET, "hello", null);

        const mockResponse = {
            status: 400
        };

        spyOn(window, 'fetch').and.returnValue(Promise.resolve(mockResponse));

        api.call(request, mockAuthorization)
            .then(success => {})
            .catch(error => {
                expect(error).toEqual(error)
                done();
            });

        expect(window.fetch).toHaveBeenCalled();
    });

    it('should return an error if response to json fails', function (done) {
        const request = new api.Request(api.requestType.GET, "hello", null);

        const mockResponse = {
            status: 200,
            json: () => Promise.reject({})
        };

        spyOn(window, 'fetch').and.returnValue(Promise.resolve(mockResponse));

        api.call(request, mockAuthorization)
            .then(success => {})
            .catch(error => {
                expect(error).toEqual(error);
                done();
            });

        expect(window.fetch).toHaveBeenCalled();
    });

});