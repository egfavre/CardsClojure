(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit :rank rank})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(defn flush? [hand]
  (let [suits (set (map :suit hand))]
    (= 1 (count suits))))

(defn straight? [hand]
  (let [ranks (sort (vec (map :rank hand)))]
    (= (nth ranks 3) (+ (nth ranks 2) 1) (+ (nth ranks 1) 2) (+ (nth ranks 0) 3))))
  
(defn four-of-a-kind? [hand]
  (let [ranks (set (map :rank hand))]
    (= 1 (count ranks))))

(defn three-of-a-kind? [hand]
  (let [ranks (sort (vec (map :rank hand)))]
    (or 
        (and (= (nth ranks 3) (nth ranks 2)) (= (nth ranks 2) (nth ranks 1)) (not= (nth ranks 0) (nth ranks 1)))
        (and (= (nth ranks 0) (nth ranks 1)) (= (nth ranks 1) (nth ranks 2)) (not= (nth ranks 2) (nth ranks 3))))))
  

(defn two-pair? [hand]
  (let [ranks (sort (vec (map :rank hand)))]
    (and (= (nth ranks 0) (nth ranks 1)) (= (nth ranks 2) (nth ranks 3)) (not= (nth ranks 1) (nth ranks 2)))))
  
      
(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        ;flushes (filter flush? hands)]
        ;straights (filter straight? hands)]
        ;straight-flushes (filter flush? (filter straight? hands))]
        ;fours (filter four-of-a-kind? hands)]
        ;threes (filter three-of-a-kind? hands)]
        pairs (filter two-pair? hands)]
    (count pairs)))
   
