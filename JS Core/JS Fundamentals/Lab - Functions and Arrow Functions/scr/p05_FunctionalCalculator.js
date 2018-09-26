function calc(a, b, op) {

    let add = function add(num1, num2) {
        return num1 + num2;
    };

    let subtraction = function sub(num1, num2) {
        return num1 - num2;
    };

    let multiply = function mul(num1, num2) {
        return num1 * num2;
    };

    let devide = function dev(num1, num2) {
        return num1 / num2;
    };

    let num1 = +a;
    let num2 = +b;

    switch (op) {
        case '+':
            return add(num1, num2);
        case '-':
            return subtraction(num1, num2);
        case '*':
            return multiply(num1, num2);
        case '/':
            return devide(num1, num2);
    }
}

console.log(calc(2, 4, '+'));
console.log(calc(3, 3, '/'));
console.log(calc(18, -1, '*'));

function calculate(firstOperand, secondOperand, operator) {
    let sum = (firstOperand, secondOperand) => firstOperand + secondOperand;
    let subtract = (firstOperand, secondOperand) => firstOperand - secondOperand;
    let multiply = (firstOperand, secondOperand) => firstOperand * secondOperand;
    let divide = (firstOperand, secondOperand) => firstOperand / secondOperand;

    let result = operator === '+' ? sum(firstOperand, secondOperand)
        : operator === '-' ? subtract(firstOperand, secondOperand)
            : operator === '*' ? multiply(firstOperand, secondOperand)
                : operator === '/' ? divide(firstOperand, secondOperand)
                    : 'Unrecognised operation';

    return result;
}

console.log(calculate(2, 4, '+'));
console.log(calculate(2, 4, '-'));
console.log(calculate(2, 4, '*'));
console.log(calculate(2, 4, '/'));

function calculate2(firstOperand, secondOperand, operator) {
    return eval(firstOperand + operator + secondOperand);
}

console.log(calculate2(2, 4, '+'));
console.log(calculate2(2, 4, '-'));
console.log(calculate2(2, 4, '*'));
console.log(calculate2(2, 4, '/'));