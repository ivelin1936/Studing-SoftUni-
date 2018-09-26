function rounding(args) {
    let num = +args[0];
    let precision = +args[1] < 15 ? +args[1] : 15;

    return +num.toFixed(precision);
}

console.log(rounding([3.1415926535897932384626433832795, 2]));
console.log(rounding([10.5, 3]));

function rounding2(args) {
    let num = +args[0];
    let precision = +args[1] < 15 ? +args[1] : 15;

    let factor = Math.pow(10, precision);

    return Math.round(num * factor) / factor;
}

console.log(rounding2([3.1415926535897932384626433832795, 2]));
console.log(rounding2([10.5, 3]));
