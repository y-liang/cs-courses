/** library */
const readline = require('readline');
const rl = readline.createInterface({
   input: process.stdin,
   output: process.stdout
});

/* every voter gets one vote, and the candidate with the most votes wins */


/**
 * inputs
 * input types are: candidates, voter count, votes
 */
function createInputPromise(inputType) {
   return new Promise((resolve, reject) => {
      rl.question(`enter the ${inputType} that are in the race: `, answer => {
         if (inputType == 'candidate names') {
            console.log(`let's check if - ${answer} - will be valid ${inputType}...`);
         } else if (inputType == 'voter counts') {
            console.log(`${inputType} is ${answer}`);
         } else {
            // vote
            console.log(`let's check if your ${inputType} for ${answer} is one of the candidates in the race...`);
         }
         resolve(answer);
      });
   });
} // prompts candidate and vote


/**
 * global variables
 */
let candidateCount, voterCount;
let candidateNames, namesArray = [];
let VotesArray = [];

class Vote {
   constructor(name, votes) {
      this.name = name;
      this.votes = votes;
   }
}

/**
 * candidates
 * prompt and ask about all the candidates, more than 1 but not exceeding 9, and put them in an array
 */
async function candidatesReady(callVotes, callResults) {

   while (true) {
      candidateNames = await createInputPromise('candidate names');
      namesArray = candidateNames.split(' ');
      candidateCount = namesArray.length;

      // check name with no numbers
      let isAtoZ = item => {
         return (item >= 'a' && item <= 'z' || item >= 'A' && item <= 'Z');
         // return true when meeting all the criteria
      };

      // check count and uniqueness
      let isUnique = (item, index, array) => {
         return array.indexOf(item) == array.lastIndexOf(item);
      };

      if (candidateCount > 1 && candidateCount < 10 && namesArray.every(isUnique) && namesArray.join('').split('').every(isAtoZ)) {
         break;
      }

      console.log(`invalid! you have entered ${candidateCount} candidates, the number should be greater than one but no more than nine and each candidate should be unique`);
   }

   console.log(`valid! you have entered ${candidateCount} candidates, and they're ${namesArray.join(', ')}`);

   // populate array with Vote instances
   // VotesArray = Array(candidateCount).fill(new Vote()); // does not work because it's the same objects multiple times, when change one instance, the others change too
   VotesArray = Array.from({ length: candidateCount }, () => new Vote);

   for (i = 0; i < candidateCount; i++) {
      VotesArray[i].name = namesArray[i];
      VotesArray[i].votes = 0;
   }

   // callback votesReady and sortResults
   callVotes(callResults);
}


/**
 * votes
 * prompt to get number of voters, so prompts number of times to ask for each voter's vote
 */
async function votesReady(callResults) {

   // let candidateCount = candidateCount; // let the local candidateCount be the same as the global one

   // minimum number of votes should be greater than the number of candidates, though not required
   while (true) {
      voterCount = await createInputPromise('voter counts');
      if (voterCount >= candidateCount) {
         break;
      }
      console.log(`the number of voters you entered is ${voterCount}, which is less than ${candidateCount}, the number of candidates. please reenter...`);
   }

   // now out of the while loop, we have a valid voter count
   console.log(`valid! we have ${voterCount} of voters`);

   // loop for each voter's vote, one vote for one candidate
   for (i = 0; i < voterCount; i++) {
      let vote;
      while (true) {
         vote = await createInputPromise('vote');
         if (namesArray.includes(vote)) {
            // add to array
            VotesArray[namesArray.indexOf(vote)].votes += 1;
            console.log(`valid! your vote for ${vote} counts`);
            break;
         }
         console.log(`invalid! your vote for ${vote} won't count, please reenter...`);;
      }
   }

   // console.log(`votes array is ${VotesArray[0]} & ${VotesArray[0].name} & ${VotesArray[0].votes} and ${VotesArray[1]} & ${VotesArray[1].name} & ${VotesArray[1].votes} and ${VotesArray[2]} & ${VotesArray[2].name} & ${VotesArray[2].votes}`);

   callResults();
}



// sort all the votes and find out winner
function sortResults() {
   let allTopVotes = [];
   let topVotes;

   /** way one, loop twice */
   // topVotes = VotesArray[0];
   // for (i = 0; i < candidateCount; i++) {
   //    if (topVotes.votes < VotesArray[i].votes) {
   //       topVotes = VotesArray[i];
   //    }
   // }   // find what the highest votes is
   // console.log('the top votes for any candidate is ' + topVotes.votes);
   // for (i = 0; i < candidateCount; i++) {
   //    if (VotesArray[i].votes == topVotes.votes) {
   //       allTopVotes.push(VotesArray[i]);
   //    }
   // }   // find who are the candidates that have the highest votes
   // console.log(`we have ${allTopVotes.length} candidate(s) who won the race`);

   /** way two, loop once */
   topVotes = VotesArray[0];
   for (i = 0; i < candidateCount; i++) {
      if (topVotes.votes < VotesArray[i].votes) {
         topVotes = VotesArray[i];
         allTopVotes = [];
         allTopVotes.push(topVotes);
      } else if (topVotes.votes == VotesArray[i].votes) {
         topVotes = VotesArray[i];
         allTopVotes.push(topVotes);
      }
   }
   console.log(`we have ${allTopVotes.length} candidate(s) who won the race`);

   /** display results */
   console.log('result is - ') + allTopVotes.forEach(element => console.log(element.name + ': ' + element.votes));
}


candidatesReady(votesReady, sortResults);
