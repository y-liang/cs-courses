
// find if there's a majority vote, more than 50%
// function findWinner() {
//    for (j = 0; j < 5; j++) {
//       if (j > 2) {
//          console.log(`we have a winner ${j}`);
//          // break;
//          return true;
//       }
//    }
//    console.log('print');
//    return false;
// }

// console.log(findWinner());


// function testBreak(x) {
//    var i = 0;

//    while (i < 6) {
//       if (i == 3) {
//          break;
//       }
//       i += 1;
//    }

//    return i * x;
// }

// console.log(testBreak(2));



// var items = [
//    { name: 'Edward', value: 21 },
//    { name: 'Sharpe', value: 37 },
//    { name: 'And', value: 45 },
//    { name: 'The', value: -12 },
//    { name: 'Magnetic', value: 13 },
//    { name: 'Zeros', value: 37 }
// ];

// // sort by value
// items.sort(function (a, b) {
//    return a.value - b.value;
// });

// console.log([...items]);



let isAtoZ = item => {
   return /^[a-zA-Z]+$/.test(item);
};

console.log(['okay', 'hello', 'apple'].every(isAtoZ));