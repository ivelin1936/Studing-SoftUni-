function uppercaseWords(text) {
    let regexPattern = /\W+/;

    let result = text
        .toUpperCase()
        .split(regexPattern)
        .filter(w => w !== '');

    console.log(result.join(', '));
}

uppercaseWords('Hi, how are you?');
uppercaseWords('hello');