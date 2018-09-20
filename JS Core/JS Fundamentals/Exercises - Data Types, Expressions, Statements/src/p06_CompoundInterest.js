function solve(args) {

    let principalSum = args[0];
    let interestRate = args[1];
    let compoundingPeriodInMonths = args[2];
    let totalTimeSpanInYears = args[3];

    let compoundFrequency = 12 * 1.0 / compoundingPeriodInMonths;

    let result = principalSum * Math.pow(
        1 + ((interestRate / 100) / compoundFrequency), compoundFrequency * totalTimeSpanInYears);

    return result.toFixed(2);
}

console.log(solve([1500, 4.3, 3, 6]));
console.log(solve([100000, 5, 12, 25]));

