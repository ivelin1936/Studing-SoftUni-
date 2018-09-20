function solve(sideA,sideB) {

    let area = sideA * sideB;
    let perimeter = 2 * (sideA + sideB);

    console.log(Math.round(area * 100) / 100);
    console.log(Math.round(perimeter * 100) / 100);
}

solve(2, 2);
solve(1, 3);
solve(2.5, 3.14);