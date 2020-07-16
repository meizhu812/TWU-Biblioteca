import  * as auth from "../../../main/webapp/js/authorization.js";

describe("authorization", function () {
    let item;
    let mockLocalStorage;

    beforeEach(function() {
        item = {};
        mockLocalStorage = {
            setItem: function(key, value) {
                item[key] = value;
            },
            getItem: function(key) {
                return item[key];
            }
        };

        spyOnProperty(window, 'localStorage').and.returnValue(mockLocalStorage);
    });

    it('should set the authorization token', function () {
        auth.setAuthorizationToken("abc-efg");

        expect(item["Token"]).toEqual("abc-efg");
    });

    it('should return the authorization', function () {
        item = { "Token" : "abc-efg" };

        expect(auth.getAuthorizationToken()).toEqual("abc-efg");
    });
});