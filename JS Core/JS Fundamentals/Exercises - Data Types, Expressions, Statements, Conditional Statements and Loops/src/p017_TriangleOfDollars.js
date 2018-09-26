function triangleOfDollars(inputNum) {
    let symbol = '$';

    for (let i = 1; i <= inputNum; i++) {
        console.log(symbol.repeat(i));
    }
}

triangleOfDollars(3);
triangleOfDollars(2);
triangleOfDollars(4);