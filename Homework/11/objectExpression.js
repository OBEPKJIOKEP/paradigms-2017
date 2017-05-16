/**
 * Created by greg on 24/04/2017.
 */
var variables = {
    "x": 0,
    "y": 1,
    "z": 2
};

var operations = {};

function Const(value) {
    this.getValue = function () {
        return value;
    }
}

Const.prototype.evaluate = function() { return this.getValue(); };
Const.prototype.toString = function() { return this.getValue().toString(); };
Const.prototype.prefix = Const.prototype.toString;

function Variable(name) {
    var index = variables[name];

    this.getName = function () {
        return name;
    };

    this.getIndex = function () {
        return index;
    }
}
Variable.prototype.evaluate = function() { return arguments[this.getIndex()]; };
Variable.prototype.toString = function() { return this.getName(); };
Variable.prototype.prefix = Variable.prototype.toString;

function AbstractExpression() {
    var operands = Array.prototype.slice.call(arguments);
    this.getOperands = function () {
        return operands;
    }
}

function expressionFactory(operation, identifier, operandNumber) {
    function Expression() {
        AbstractExpression.apply(this, arguments);
    }
    Expression.prototype = Object.create(AbstractExpression);
    Expression.prototype.getOperation = function () { return operation; };
    Expression.prototype.getIdentifier = function () { return identifier; };
    Expression.prototype.getOperandNumber = function () { return operandNumber;};
    Expression.prototype.evaluate = function() {
        var vars = arguments;
        var mapped = Array.prototype.map.call(this.getOperands(), function(x) { return x.evaluate.apply(x, vars); });

        return this.getOperation().apply(null, mapped);
    };
    Expression.prototype.toString = function() {
        return this.getOperands().join(" ") + " " + this.getIdentifier();
    };
    Expression.prototype.prefix = function() {
        return "(" + this.getIdentifier() + " " +
            this.getOperands().map(function (e) { return e.prefix();}).join(" ") + ")";
    };

    operations[identifier] = Expression;
    return Expression;
}

var Add = expressionFactory(function(a, b) { return a + b; }, "+", 2);

var Subtract = expressionFactory(function(a, b) { return a - b; }, "-", 2);

var Negate = expressionFactory(function (a) { return -a; }, "negate", 1);

var Multiply = expressionFactory(function(a, b) { return a * b; }, "*", 2);

var Divide = expressionFactory(function(a, b) { return a / b; }, "/", 2);

var Sin = expressionFactory(function (a) { return Math.sin(a); }, "sin", 1);

var Cos = expressionFactory(function (a) { return Math.cos(a); }, "cos", 1);

var Power = expressionFactory(function (a, b) { return Math.pow(a, b) }, "pow", 2);

var Log = expressionFactory(function (a, b) { return Math.log(Math.abs(b)) / Math.log(Math.abs(a)) }, "log", 2);

var whitespaces = /\s/;

function parsePrefix(string) {
    var currentIndex = 0;
    var expression = string + '\0';

    function UnexpectedTokenError(expected, found, currentInd) {
        this.name = "UnexpectedTokenError";
        if (currentInd !== undefined) {
            this.message = expected + " expected, but found " +
                 (found + " starting at position " + (currentInd + 1));
        } else {
            this.message = expected + " expected, but found " +
                (found === "\'\0\'"
                    ? ("end of expression" + " starting at position " + (currentIndex + 1))
                    : (found + " starting at position " + (currentIndex - found.length + 3)));
        }
    }
    UnexpectedTokenError.prototype = Object.create(Error.prototype);

    function IncorrectOperandNumberError(identifier, foundOperands, startingPos) {
        this.name = "IncorrectOperandNumberError";
        var identifierObj = new operations[identifier];
        this.message = "Expected " + identifierObj.getOperandNumber() + " operands for operation \'" + identifier +
            "\' starting at position " + startingPos + ", but found " + foundOperands;
    }
    IncorrectOperandNumberError.prototype = Object.create(Error.prototype);

    function UnknownIdentifierError(found) {
        this.name = "UnknownIdentifierError";
        this.message = "Unknown identifier \'" + found + "\' starting at position " + (currentIndex - found.length + 1);
    }
    UnknownIdentifierError.prototype = Object.create(Error.prototype);

    function takeCharsUntil(predicate) {
        var begin = currentIndex;
        while (predicate(expression[currentIndex])) {
            currentIndex++;
        }
        return expression.slice(begin, currentIndex);
    }

    function isWhitespace(c) {
        return c === ' ';
    }

    function abstractIs(lower, upper) {
        return function(c) {
            return lower <= c && c <= upper;
        }
    }

    var isLetter = abstractIs('a', 'z');
    var isDigit = abstractIs('0', '9');
    function takeCharsUntilEOE() {
        return takeCharsUntil(function (c) {
            return !isWhitespace(c) && c !== '(' && c !== ')' && c !== '\0';
        });
    }

    function currentChar() {
        return expression[currentIndex];
    }

    function skipWhitespaces() {
        while (isWhitespace(currentChar())) {
            currentIndex++;
        }
    }

    function parse() {
        skipWhitespaces();
        if (currentChar() === '(') {
            currentIndex++;
            skipWhitespaces();
            var identifierStart = currentIndex;
            var identifier = takeCharsUntilEOE();
            if (!(identifier in operations)) {
                if (identifier.length === 0) {
                    throw new UnexpectedTokenError("Operation after opening parenthesis", "empty operation", currentIndex);
                }
                throw new UnexpectedTokenError("Operation after opening parenthesis", '\'' + identifier + '\'');
            }
            var operNumber = (new operations[identifier]).getOperandNumber();
            var operands = [];
            for (var i = 0; i < operNumber; i++) {
                if (currentChar() === ')') {
                    throw new IncorrectOperandNumberError(identifier, i, identifierStart);
                }
                operands.push(parse());
            }
            skipWhitespaces();
            if (currentChar() !== ')') {
                if (currentChar() === '\0') {
                    throw new UnexpectedTokenError("Closing parenthesis", "end of expression", currentIndex);
                }
                var operandsCount = 0;
                while (currentChar() !== ')' && currentChar() !== '\0') {
                    parse();
                    operandsCount++;
                }
                throw new IncorrectOperandNumberError(identifier, operNumber + operandsCount, identifierStart);
            }
            currentIndex++;
            var result = Object.create(operations[identifier].prototype);
            operations[identifier].apply(result, operands);
            return result;
        } else if (isLetter(currentChar())) {
            var name = takeCharsUntil(isLetter);
            if (!(name in variables)) {
                throw new UnknownIdentifierError(name);
            }
            return new Variable(name);
        } else if (isDigit(currentChar()) || currentChar() === '-'  || currentChar() === '+') {
            var numberStr = [currentChar()];
            currentIndex++;
            if ((numberStr[0] === "-" && !isDigit(currentChar())) ||
                    numberStr[0] === "+") {
                var identifier = takeCharsUntilEOE();
                throw new UnexpectedTokenError("Correctly formatted number", '\'' + (numberStr + identifier) + '\'');
            }
            numberStr += takeCharsUntil(isDigit);
            var number = parseInt(numberStr);
            if (isNaN(number)) {
                throw new UnexpectedTokenError("Number", '\'' + numberStr + '\'');
            }
            return new Const(number);
        } else {
            throw new UnexpectedTokenError("Expression", '\'' + currentChar() + '\'');
        }

    }
    var answer = parse();
    skipWhitespaces();
    if (currentChar() !== '\0') {
        throw new UnexpectedTokenError("End of expression", currentChar());
    }
    return answer;
}
//parsePrefix("(+ (+ x y) (+ x y))").prefix();