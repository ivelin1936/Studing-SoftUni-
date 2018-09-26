function solve(args) {
    let firstV = +args[0];
    let secondV = +args[1];
    let periodT = +args[2] / 3600;

    let firstObjDist = firstV * periodT * 1000;
    let secondObjDist = secondV * periodT * 1000;

    console.log(Math.abs(firstObjDist - secondObjDist));
}

solve([0, 60, 3600]);
solve([11, 10, 120]);
solve([5, -5, 40]);