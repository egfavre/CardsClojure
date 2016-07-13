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
  (let [ranks (sorted-set (map :rank hand))]
    (let [[a b c d] ranks]
      ((= d (+ c 1) (+ b 2) (+ a 3))))))
    
(defn straight-flush? [hand]
  (let [suits (set (map :suit hand))]
       [ranks (sorted-set (map :rank hand))]
       [[a b c d] ranks]
    (cond
      (= 1 (count suits))
      ((= d (+ c 1) (+ b 2) (+ a 3))))))

(defn four-of-a-kind? [hand]
  (let [ranks (set (map :rank hand))]
    (= 1 (count ranks))))

(defn three-of-a-kind? [hand]
  (let [[a b c d] ranks]
    (!= a b c d))
  (let [ranks (sorted-set (map :rank hand))]    
    (= 2 (count ranks))))

(defn two-pair? [hand]
  (let [[a b c d] hand]
    (and (= a b) (= c d))))
    
    
(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        flushes (filter flush? hands)
        straights (filter straight? hands)
        straight-flushes (filter striaght-flushes? hands)
        fours (filter four-of-a-kind? hands)
        threes (filter three-of-a-kind? hands)
        two-pairs (filter two-pair? hands)]
    (count flushes)
    (count straights)
    (count stright-flushes)
    (count fours)
    (count threes)
    (count two-pairs)))
