
const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});



/**
 * step 1
 * prompt user input for key
 */

// the Promise object represents the eventual completion (or failure) of an asynchronous operation, and its resulting value
// Promise can only resolve once

// here, have to call this function twice to create two different Promise objects

function createInputPromise(inputType) {
  return new Promise((resolve, reject) => {
    rl.question(`enter your ${inputType}: `, (answer) => {
      if (inputType == 'key to translate') {
        console.log(`let's check if ${answer} is a valid ${inputType}...`);
      } else {
        console.log(`${inputType} is ${answer}`);
      }
      resolve(answer);

      // rl.close(); // do not call close here if you want to continue using readline
    });
  });
}


let key, validKey, translateType, beforeText, afterText;

/**
 * 
 */

// this function will only run when inputReady() finishes
function translateText(key, type, text) {

  afterTextArray = []; // has to be here, otherwise undefined

  for (i = 0; i < beforeText.length; i++) {
    letterCode = beforeText.charCodeAt(i);

    if (beforeText[i] >= 'a' && beforeText[i] <= 'z' || beforeText[i] >= 'A' && beforeText[i] <= 'Z') {
      afterTextArray.push(String.fromCharCode(
        translateType == 'encrypt' ?
          (letterCode + (key % 26)) // encrypt the text
          : (letterCode - (key % 26)))); // decipher the text
    }

  }

  afterText = afterTextArray.join('');

  console.log(`we have ${type}ed ${text} with ${key}, the result is - ${afterText}`);
}



/**
 * 
 */



async function inputReady(callTranslate) {

  /** step 1 - get valid key - to be checked */
  // can't be while key because key's not there yet
  while (true) {
    key = await createInputPromise('key to translate');

    if (key > 0 && key - Math.floor(key) == 0) {
      break;
    }

    console.log(`${key} is invalid. pick a another one`);
  }

  console.log(`valid key! now ${key} is your key for translation`);

  validKey = key;

  /** step 2 - get translate type - encrypt or decipher */
  translateType = await createInputPromise('translate type');


  /** step 3 - get text - any text */
  beforeText = await createInputPromise('plain text');


  /** now we have all three inputs key, text, and type */
  console.log(`we will now translate ${translateType} - ${beforeText} - with ${validKey}`);


  /** after the inputs are ready, callback translate function */
  callTranslate(validKey, translateType, beforeText);
}


inputReady(translateText);

