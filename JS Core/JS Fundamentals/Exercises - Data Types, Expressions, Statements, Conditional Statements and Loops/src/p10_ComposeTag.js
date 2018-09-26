function composeTag(args) {
    let fileLocation = args[0];
    let alternateText = args[1];

    return `<img src="${fileLocation}" alt="${alternateText}">`;
}

console.log(composeTag(['smiley.gif', 'Smiley Face']));