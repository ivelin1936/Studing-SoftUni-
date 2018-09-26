function dayOfWeek(input) {
    switch (input.toLowerCase()) {
        case 'monday':
            return 1;
            break;
        case 'tuesday':
            return 2;
            break;
        case 'wednesday':
            return 3;
            break;
        case 'thursday':
            return 4;
            break;
        case 'friday':
            return 5;
            break;
        case 'saturday':
            return 6;
            break;
        case 'sunday':
            return 7;
            break;
        default:
            return 'error';
    }
}

console.log(dayOfWeek('Monday'));
console.log(dayOfWeek('mondAy'));
console.log(dayOfWeek('friday'));
console.log(dayOfWeek('Frabjoyousday'));

function weekDay(input) {
    let weekDays = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];

    for (let day of weekDays) {
        if (day === input.toLowerCase()) {
            return weekDays.indexOf(input.toLowerCase()) + 1;
        }
    }
    return 'error';
}

console.log(weekDay('Monday'));
console.log(weekDay('mondAy'));
console.log(weekDay('friday'));
console.log(weekDay('Frabjoyousday'));