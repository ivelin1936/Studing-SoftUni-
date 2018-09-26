function moviePrice(input) {

    let title = input[0].toLowerCase();
    let day = input[1].toLowerCase();

    let price;
    switch (title) {
        case 'the godfather':
            if (day === 'monday') {
                price = 12;
            } else if (day === 'tuesday') {
                price = 10;
            } else if (day === 'wednesday') {
                price = 15;
            } else if (day === 'thursday') {
                price = 12.50;
            } else if (day === 'friday') {
                price = 15;
            } else if (day === 'saturday') {
                price = 25;
            } else if (day === 'sunday') {
                price = 30;
            } else {
                return 'error';
            }
            break;
        case "schindler's list":
            if (day === 'monday') {
                price = 8.50;
            } else if (day === 'tuesday') {
                price = 8.50;
            } else if (day === 'wednesday') {
                price = 8.50;
            } else if (day === 'thursday') {
                price = 8.50;
            } else if (day === 'friday') {
                price = 8.50;
            } else if (day === 'saturday') {
                price = 15;
            } else if (day === 'sunday') {
                price = 15;
            } else {
                return 'error';
            }
            break;
        case 'casablanca':
            if (day === 'monday') {
                price = 8;
            } else if (day === 'tuesday') {
                price = 8;
            } else if (day === 'wednesday') {
                price = 8;
            } else if (day === 'thursday') {
                price = 8;
            } else if (day === 'friday') {
                price = 8;
            } else if (day === 'saturday') {
                price = 10;
            } else if (day === 'sunday') {
                price = 10;
            } else {
                return 'error';
            }
            break;
        case 'the wizard of oz':
            if (day === 'monday') {
                price = 10;
            } else if (day === 'tuesday') {
                price = 10;
            } else if (day === 'wednesday') {
                price = 10;
            } else if (day === 'thursday') {
                price = 10;
            } else if (day === 'friday') {
                price = 10;
            } else if (day === 'saturday') {
                price = 15;
            } else if (day === 'sunday') {
                price = 15;
            } else {
                return 'error';
            }
            break;
        default :
            return 'error';
            break;
    }

    return price;
}

console.log(moviePrice(['The Godfather', 'Friday']));
console.log(moviePrice(['casablanca', 'sunday']));
console.log(moviePrice(["Schindler's LIST", 'monday']));
console.log(moviePrice(['SoftUni', 'Nineday']));