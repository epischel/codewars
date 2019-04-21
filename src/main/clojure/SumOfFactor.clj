(defn findAbsMax 
  "finds absolute maximum of a list of integers"
  [lst]
  (apply max (map #(Math/abs %)  lst))
)

(defn makeRange 
  "creates sufficient range, i.e. 2..sqrt(n)"
  [n]
  (range 2 (+ 1 (int (Math/sqrt n))))
)


(defn isPrime [n]
  (empty? (filter (fn [x] (zero? (mod n x))) (makeRange n))
  )
)

(defn listOfPrimes [max] 
  (filter #(isPrime %) (range 2 (inc max)))
)


(defn multiples 
  "returns all integers in lst that have prime as a prime factor"
  [lst prime]
  (filter (fn [x] (zero? (mod x prime))) lst)
)

(defn sumMultiples
  "return the sum of all i in lst where prime is prime factor or nil otherwise"
  [lst prime]
  (let [multiplesOfThisPrime (multiples lst prime)]
    (if (empty? multiplesOfThisPrime)
      nil
      [prime, (reduce + multiplesOfThisPrime)]
    )
  )  
)

(defn primeAndSum
  "return pairs (prime, sumMultiples)"
  [lst primes]
  (filter (fn [x] (not (nil? x)))
    (map (fn [x] (sumMultiples lst x)) primes))
)

(defn sum-of-divided [lst] 
  (primeAndSum lst (listOfPrimes (findAbsMax lst)))
)
