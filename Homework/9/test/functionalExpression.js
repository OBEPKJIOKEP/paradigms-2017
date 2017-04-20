/**
 * Created by greg on 10/04/2017.
 */


var add = abstractOperation(function (a, b) {
    return a + b;
})

var subtract = abstractOperation(function (a, b) {
    return a - b;
})

var multiply = abstractOperation(function (a, b) {
    return a * b;
})

var divide = abstractOperation(function (a, b) {
    return a / b;
})

var negate = abstractOperation(function (a) {
    return -a;
});

var e = cnst(Math.E), pi = cnst(Math.PI), x = variable("x"), y = variable("y"), z = variable("z");

function abstractOperation(operation) {
    return function () {
        var operands = arguments;
        return function () {
            var variables = arguments;
            var mapped = map(function(current) { return current.apply(null, variables)}).apply(null, operands)
            return operation.apply(null, mapped);
        }
    }
}

function cnst(value) {
    return function () {
        return value;
    };
}

function variable(value) {
    var index = ["x", "y", "z", "u", "v", "w"].indexOf(value);
    return function () {
        return arguments[index];
    };
}

function map(f) {
    return function() {
        var result = [];
        for (var i = 0; i < arguments.length; i++) {
            result.push(f(arguments[i]));
        }
        return result;
    }
}