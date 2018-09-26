function square(size = 5) {
    let symbol = '*';

    for (let i = 1; i <= size; i++) {
        console.log(`${symbol} `.repeat(size));
    }
}

square(1);
console.log();
square(2);
console.log();
square(0);

function squareStars(size = 5) {
    let square = '';
    let symbol = '*';

    for (let i = 1; i <= size; i++) {
        square += symbol + ` ${symbol}`.repeat(size - 1) + '\r\n';
    }

    console.log(square);
}

triangleStars(1);
console.log();
triangleStars(2);
console.log();
triangleStars(0);