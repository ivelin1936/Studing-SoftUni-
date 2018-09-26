function oddNumbers(n) {
    for (let i = 1; i <= n; i++) {
        let x = i % 2;
        if (x !== 0) {
            console.log(i);
        }
    }
}

oddNumbers(5);
oddNumbers(4);
oddNumbers(7);