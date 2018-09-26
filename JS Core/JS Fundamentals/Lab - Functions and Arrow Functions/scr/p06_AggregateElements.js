function aggregate(arr) {

    let result1 = arr.reduce((a, b) => a + b, 0);

    let result2 = function (arr) {
        let sum = 0;
        for (let i = 0; i < arr.length; i++) {
            sum += 1 / arr[i];
        }
        return sum;
    };

    let result3 = arr.join('');

    console.log(result1);
    console.log(result2(arr));
    console.log(result3)
}

aggregate([1, 2, 3]);
aggregate([2, 4, 8, 16]);

function printAggregation(params) {

    function aggregate(initialValue, func) {
        let result = initialValue;

        for (num of params) {
            result = func(result, num);
        }

        return result;
    }

    console.log(aggregate(0, (result, num) => result + num));
    console.log(aggregate(0, (result, num) => result + 1 / num));
    console.log(aggregate('', (result, num) => result + num));
}

printAggregation([1, 2, 3]);