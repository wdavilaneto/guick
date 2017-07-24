(function () {
    'use strict';

    describe("The Hitchhiker's Guide to the Galaxy Tests", function() {

        var answer;

        beforeEach(function() {
            answer = "42";
        }

        it("Should know the Answer to the Ultimate Question of Life, The Universe, and Everything", function () {
            expect(answer).toEqual("42");
        });

    });

})();
