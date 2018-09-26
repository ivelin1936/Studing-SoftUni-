function palindrome(inputString) {
    let reverseStr = inputString.split('').reverse().join('');

    if (inputString === reverseStr) {
        return true;
    } else {
        return false;
    }
}

console.log(palindrome("haha"));
console.log(palindrome("racecar"));
console.log(palindrome("unitinu"));

function isPalindrom(word) {
    for (let i = 0; i < Math.ceil(word.length); i++) {
        if (word[i] !== word[word.length - i - 1]) {
            return false;
        }
    }
    return true;
}

console.log(isPalindrom("haha"));
console.log(isPalindrom("racecar"));
console.log(isPalindrom("unitinu"));