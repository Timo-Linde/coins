(ns coins.reader
  (:require [clojure.string :as s]))

(defn usd->euro
  [euro-currency-rate usd]
  (/ usd euro-currency-rate))

(defn euro->usd
  [euro-currency-rate euro]
  (* euro euro-currency-rate))

(defn g->oz
  [g]
  (/ g 31.1034768))

(defn oz->g
  [oz]
  (* oz 31.1034768))

(defn gold-coin?
  [coin]
  (= (s/lower-case (:material coin)) "gold"))

(defn silver-coin?
  [coin]
  (= (s/lower-case (:material coin)) "silber"))


(defn rating->rating-color
  [rating]
  (case rating
    (1 2) "red"
    3 "yellow"
    (4 5) "green"))

(defn coin-pure-weight
  [purity weight]
  (/ (* weight purity) 1000))

(defn coin-material-price
  [gold-price silver-price coin]
  (cond
    (gold-coin? coin) gold-price
    (silver-coin? coin) silver-price))

(defn coin-weight-in-oz
  [{:keys [weight weightType] :as coin}]
  (double (case weightType
            "g" (g->oz weight)
            "oz" weight)))

(defn coin-value
  [{:keys [purity] :as coin} price-calculator]
  (* (price-calculator) (coin-pure-weight purity (coin-weight-in-oz coin))))

(defn enrich-coin
  [{:keys [gold-price silver-price _coins] :as _conf} {:keys [rating amount] :as coin}]
  (let [value (coin-value coin (partial coin-material-price gold-price silver-price coin))]
    (merge coin {
                 :ratingColor (rating->rating-color rating)
                 :value       value
                 :amountValue (* value amount)
                 })))

(defn enriched-coins
  [{:keys [coins] :as conf}]
  (assoc-in conf [:coins] (map (partial enrich-coin conf) coins)))

(def gold-coins (partial filter gold-coin?))
(def silver-coins (partial filter silver-coin?))