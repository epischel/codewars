// see https://www.codewars.com/kata/sum-by-factors/train/javascript

// find prime numbers up to "max" - naive implementation
function primes(max) {
  if (max<2) {
    return new Array();
  }
  let result = new Array();
  result.push(2);
  for (let i = 3; i <= max; i++) {
    let found=false;
    for(let j = 2; j < Math.sqrt(i)+1 && !found; j++) {
      if ((i%j)==0) {
        found=true;
      }
    }
    if (!found) result.push(i);
  }
  return result;
}

function findAbsMax(list) {
  return list.map(x=>Math.abs(x)).reduce((a,b)=>Math.max(a,b),0)
}

function sumOfDivided(lst) {
  let listOfPrimes = primes(findAbsMax(lst));
  let result = new Array();
  listOfPrimes.forEach((value, index)=> {
    let sum = 0, count=0;
    lst.forEach((valueList, indexList)=> {
      if (valueList % value == 0) {
         sum += valueList;
         count++;
      }
    });
    if (count>0) {
      result.push([value, sum]);
    }
  });
  return result;
}