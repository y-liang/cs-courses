const ALPHABET = 'abcdefghijklmnopqrstuvwxyz';



function translateText(key, type, text) {
  key = [];

  let textArray = text.split('');
  let afterTextArray = [];

  if (type == 'encrypt') {
    afterTextArray = textArray.map(
      item => {
        if (item >= 'a' && item <= 'z') {
          item = key[ALPHABET.indexOf(item)];
        } else if (item >= 'A' && item <= 'Z') {
          console.log(key);
          item = key[ALPHABET.indexOf(item)].toUpperCase(); // Cannot read property 'toUpperCase' of undefined
        } else {
          item;
        }
      }
    );
  } else {
    // decipher
    afterTextArray = textArray.map(
      item => {
        if (item >= 'a' && item <= 'z') {
          item = ALPHABET[key.indexOf(item)];
        } else if (item >= 'A' && item <= 'Z') {
          item = ALPHABET[key.indexOf(item)].toUpperCase();
        } else {
          item;
        }
      }
    );
  }

  afterText = afterTextArray.join('');

  console.log('now encrypted' + afterText);

}
