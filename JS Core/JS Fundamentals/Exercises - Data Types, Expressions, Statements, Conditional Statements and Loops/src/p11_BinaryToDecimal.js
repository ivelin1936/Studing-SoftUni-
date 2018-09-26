function getDecimal(binaryNum) {
    let digit = parseInt(binaryNum, 2);

    return digit;
}

console.log(getDecimal('00001001'));
console.log(getDecimal('11110000'));