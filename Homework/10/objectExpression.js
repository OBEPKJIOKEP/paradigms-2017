/**
 * Created by greg on 24/04/2017.
 */

var variables = ["x", "y", "z"];

function Const(value) {
    this._value = value;
    Object.freeze(this);
}

Const.prototype.evaluate = function() { return this._value; };

Const.prototype.toString = function() { return this._value.toString(); };

function Variable(name) {
    this._index = variables.indexOf(name);
    Object.freeze(this);
}

Variable.prototype.evaluate = function() {
    return arguments[this._index];
};

Variable.prototype.toString = function() { return variables[this._index]; };

function AbstractExpression() {
    this._operands = arguments;
    Object.freeze(this);
    Object.freeze(this._operands);
}

AbstractExpression.prototype.evaluate = function() {
    var variables = arguments;
    var mapped = Array.prototype.map.call(this._operands, function(x) { return x.evaluate.apply(x, variables); });
    return this._operation.apply(null, mapped);
};

AbstractExpression.prototype.toString = function() {
    return Array.prototype.join.call(this._operands, " ") + " " + this._strIdentifier;
};

function expressionFactory(operation, strIdentifier) {
    function Expression() {
        AbstractExpression.apply(this, arguments);
    }
    Expression.prototype = Object.create(AbstractExpression.prototype);
    Expression.prototype._operation = operation;
    Expression.prototype._strIdentifier = strIdentifier;
    return Expression;
}

var Add = expressionFactory(function(a, b) { return a + b; }, "+");

var Subtract = expressionFactory(function(a, b) { return a - b; }, "-");

var Negate = expressionFactory(function (a) { return -a; }, "negate");

var Multiply = expressionFactory(function(a, b) { return a * b; }, "*");

var Divide = expressionFactory(function(a, b) { return a / b; }, "/");

var Power = expressionFactory(function(a, b) { return Math.pow(a, b); }, "pow");

var Log = expressionFactory(function(x, base) {
    return Math.log(Math.abs(base)) / Math.log(Math.abs(x));
}, "log");


