
/**
 * async before a function means one thing
 * a function always returns a promise
 */

async function getKey() {
   rl.question('Pick a non-negative integer to be your key ', (answer) => {
      console.log(`You want to use ${answer} as a key, let's check if that's okay`);

      // explicitly return a promise
      return Promise.resolve(answer);

      // or implicitly return a promise like this
      // return answer;
   });
};




/** way one - start */

function createInputPromise(inputType) {
   return new Promise((resolve, reject) => {
      rl.question(`enter your ${inputType}: `, (answer) => {
         console.log(`let's check if ${answer} is a valid ${inputType}`);
         resolve(answer);
      });
   });
}


async function setKey() {

   let key;
   // check key
   while (key < 0 || key - Math.floor(key) != 0) {
      key = await createInputPromise('encryption key'); // wait until the promise resolves
   }

   console.log(`valid key! now ${key} is your key for encryption`);
   return key;
}

setKey();


async function setText() {

   let text;
   text = await createInputPromise('plain text before encryption');

   console.log(`you're going to encrypt ${text} now`);
   return text;
}

setText();


/** way one - end */


/** way two - start */

function createInputPromise(inputType) {
   return new Promise((resolve, reject) => {
      rl.question(`enter your ${inputType}: `, (answer) => {
         console.log(`let's check if ${answer} is a valid ${inputType}`);
         resolve(answer);
      });
   });
}


const keyPromise = createInputPromise('encryption key');
const textPromise = createInputPromise('plain text');

let validKey, plainText;



keyPromise
   .then(function (value) {
      let key;
      while (key < 0 || key - Math.floor(key) != 0) {
         key = value;
      }

      validKey = key;

      console.log(`valid key! now ${validKey} is your key for encryption`);
      // return key;
   });

textPromise
   .then(function (value) {
      plainText = value;

      console.log(`you're going to encrypt ${plainText} now`);
      // return text;
   });


/** way two - end */



/** way three - start */

function createInputPromise(inputType) {
   return new Promise((resolve, reject) => {
      rl.question(`enter your ${inputType}: `, (answer) => {
         if (inputType == 'encryption key') {
            console.log(`let's check if ${answer} is a valid ${inputType}`);
            while (answer < 0 || answer - Math.floor(answer) != 0) {
               keyPromise = await createInputPromise('encryption key'); // run first, and wait until the promise resolves
               answer = keyPromise;
            }
            console.log(`valid key! now ${answer} is your key for encryption`);
         }
         else {
            console.log(`${inputType}! let's encrypt ${answer}`);
         };


         resolve(answer);

         // rl.close(); // do not call close here if you want to continue using readline
      });
   });
}

/** way three - end */



/** way four - start */

async function encryption() {

   // const keyPromise = createInputPromise('encryption key'); // execute immediately concurrently
   // const textPromise = createInputPromise('plain text'); // execute immediately concurrently

   // use await to make it async
   // const keyPromise = await createInputPromise('encryption key');
   // console.log(keyPromise); // run first, returns a Promise obj for key
   // const textPromise = await createInputPromise('plain text');
   // console.log(textPromise); // run second, returns a Promise obj for text

   let key, validKey, plainText;


   while (true) {
      key = await createInputPromise('encryption key');

      if (key > 0 && key - Math.floor(key) == 0) {
         break;
      }

      console.log(`${key} is invalid. pick a another one`);
   }

   console.log(`valid key! now ${key} is your key for encryption`);
   validKey = key;

   plainText = await createInputPromise('plain text'); // run latter, and also wait

   console.log(validKey, plainText);


   let encryptedArray = [];

   for (i = 0; i < plainText.length; i++) {

      letterCode = plainText.charCodeAt(i);

      // none of the comma, the period, the space and the like were “shifted” by the cipher
      // only rotate alphabetical characters
      // and keep the case of original text

      if (plainText[i] >= 'a' && plainText[i] <= 'z' || plainText[i] >= 'A' && plainText[i] <= 'Z') {
         encryptedArray.push(String.fromCharCode((letterCode + (key % 26))));
      } else {
         encryptedArray.push(String.fromCharCode(letterCode));
      }

   }

   // console.log(encryptedArray.join(''));

   return encryptedArray.join(''); // here only for it to be tested

}


encryption();

async function decipher() {

   let key, validKey, encryptedText;

   while (true) {
      key = await createInputPromise('encryption key'); // run first, and wait until the promise resolves

      if (key < 0 || key - Math.floor(key) != 0) {
         console.log(`${key} is not valid, please reenter a new one`);
      } else {
         break;
      }
   }


   console.log(`valid key! now ${key} is your key for encryption`);
   validKey = key;

   encryptedText = await createInputPromise('encrypted text'); // run latter, and also wait

   console.log(validKey, encryptedText);


   let decipheredArray = [];

   for (i = 0; i < encryptedText.length; i++) {

      letterCode = encryptedText.charCodeAt(i);

      // none of the comma, the period, the space and the like were “shifted” by the cipher
      // only rotate alphabetical characters
      // and keep the case of original text

      if (encryptedText[i] >= 'a' && encryptedText[i] <= 'z' || encryptedText[i] >= 'A' && encryptedText[i] <= 'Z') {
         decipheredArray.push(String.fromCharCode((letterCode - (key % 26))));
      } else {
         decipheredArray.push(String.fromCharCode(letterCode));
      }

   }

   console.log(decipheredArray.join(''));

   return decipheredArray.join(''); // here only for it to be tested




};


// decipher();
/** way four - end */



/** 
 * way to check uniqueness in an array - start
 * 
 */

let isAtoZ = item => {
   return (item >= 'a' && item <= 'z' || item >= 'A' && item <= 'Z');
   // return true when meeting all the criteria
};

// let isUnique = (item, index, array) => {
//   let arrayOriginal = [...array]; // spread to copy the array
//   let arrayReversed = [...array].reverse();
//   return (
//     arrayOriginal.indexOf(item) == (array.length - 1) - arrayReversed.indexOf(item)
//   );
// };

let isUnique = (item, index, array) => {
   return (
      array.indexOf(item) == array.lastIndexOf(item)
   );
};

let keyArray = 'abcdefghijklmnopqrstuvwxyz'.split('');

// console.log(keyArray);
// console.log(keyArray.reverse());
// console.log(keyArray.reverse());

let keyArrayOriginal = [...keyArray]; // has to use spread here to avoid conflicts
let keyArrayReversed = [...keyArray].reverse();

console.log(keyArrayOriginal);
console.log(keyArrayReversed);

console.log(keyArrayOriginal.indexOf('a'));
console.log(keyArrayReversed.indexOf('a'));


console.log(keyArrayOriginal.indexOf('z'));
console.log(keyArrayReversed.indexOf('z'));


console.log(keyArrayOriginal.indexOf('i'));
console.log(keyArrayReversed.indexOf('i'));

// third check key to be 26-character and alphabet
console.log(keyArray.length == 26);

console.log(keyArray.every(isAtoZ));
console.log(keyArray.every(isUnique));

/**
 * way to check uniqueness in an array - end
 *
 */
