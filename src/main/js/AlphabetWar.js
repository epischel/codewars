// see https://www.codewars.com/kata/alphabet-war

MAP = new Map();
MAP.set('w', -4);
MAP.set('p', -3);
MAP.set('b', -2);
MAP.set('s', -1);
MAP.set('m', 4);
MAP.set('q', 3);
MAP.set('d', 2);
MAP.set('z', 1);

function alphabetWar(fight)
{
   let sum=0;
   for(var i = 0; i < fight.length; i++) {
     sum += MAP.get(fight.charAt(i))||0; // nice property of javascript short circuit eval
   }
   if (sum==0) {
     return "Let's fight again!";
   }
   return ((sum<0)?"Left":"Right")+" side wins!";
}