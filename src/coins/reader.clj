(ns coins.reader
  (:require [clojure.string :as s]
            [common.converter :as converter]))

(defn gold-coin?
  [coin]
  (-> coin
      :material
      (s/lower-case)
      (= "gold")))

(defn silver-coin?
  [coin]
  (-> coin
      :material
      (s/lower-case)
      (= "silber")))

(defn rating->rating-color
  [rating]
  (case rating
    (1 2) "red"
    3 "yellow"
    (4 5) "green"))

(defn coin-material-price
  [gold-price silver-price coin]
  (cond
    (gold-coin? coin) gold-price
    (silver-coin? coin) silver-price))

(defn coin-weight-in-oz
  [{:keys [weight weightType] :as coin}]
  (double (case weightType
            "g" (converter/g->oz weight)
            "oz" weight)))

(defn coin-value
  [{:keys [purity] :as coin} price-calculator]
  (* (price-calculator) (converter/pure-weight purity (coin-weight-in-oz coin))))

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