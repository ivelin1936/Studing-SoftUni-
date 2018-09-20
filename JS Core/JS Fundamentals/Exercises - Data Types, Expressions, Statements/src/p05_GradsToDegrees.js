function convert(grads) {

    let degrees = (grads * 0.90) % 360;

    return degrees >= 0 ? degrees : 360 + degrees;
}

console.log(convert(100));
console.log(convert(400));
console.log(convert(850));
console.log(convert(-50));
