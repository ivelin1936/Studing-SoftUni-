function triangleStars(n) {
    let symbol = '*';

    for (let i = 1; i <= n; i++) {
        console.log(symbol.repeat(i));
    }

    for (let j = n - 1; j >= 1; j--) {
        console.log(symbol.repeat(j));
    }
}

triangleStars(1);
console.log();
triangleStars(2);
console.log();
triangleStars(5);