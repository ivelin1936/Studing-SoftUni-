function composesObj(args) {
    let obj = {};

    if (args.length <= 1) {
        return obj;
    }

    for (let i = 0; i < args.length - 1; i++) {
        obj[args[i++]] = args[i];
    }

    return obj;
}

console.log(composesObj(['name', 'Pesho', 'age', '23', 'gender', 'male']));
console.log(composesObj(['ssid', '90127461', 'status', 'admin', 'expires', '600']));