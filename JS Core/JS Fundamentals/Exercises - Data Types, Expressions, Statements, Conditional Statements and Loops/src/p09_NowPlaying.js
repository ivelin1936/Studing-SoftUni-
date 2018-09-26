function getInfo(args) {
    let artistName = args[1];
    let trackName = args[0];
    let duration = args[2];

    return `Now Playing: ${artistName} - ${trackName} [${duration}]`
}

console.log(getInfo(['Number One', 'Nelly', '4:09']));