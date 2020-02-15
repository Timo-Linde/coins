(ns common.converter)

(defn usd->euro
  [euro-currency-rate usd]
  (/ usd euro-currency-rate))

(defn euro->usd
  [euro-currency-rate euro]
  (* euro euro-currency-rate))

(defn switch-rate
  "e.g. usd to euro rate calculated to euro to usd rate"
  [rate]
  (/ 1 rate))

(defn g->oz
  [g]
  (/ g 31.1034768))

(defn oz->g
  [oz]
  (* oz 31.1034768))

(defn pure-weight
  "Purity 100% = 1000 1% = 10"
  [purity weight]
  (/ (* weight purity) 1000))