(ns coins.handler
  (:require [compojure.core :as compojure :refer [GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :as ring]
            [coins.html.coins :as coin-html]
            [coins.reader :as coins-reader]
            [coins.html.layout :as layout]
            [clojure.data.json :as json]
            [coins.rates :as rates]))
;; https://data-asg.goldprice.org/dbXRates/EUR

;;  (slurp "https://timo-linde.de/coins.json"

(defn- parse-coins-json
  [json]
  (-> json
      (json/read-str :key-fn keyword)))


(defn- coins
  []
  (let [coins-json (slurp "resources/coins.json")
        rates-json (slurp "resources/rates.json")]
    (-> coins-json
        (parse-coins-json)
        (merge (rates/json->rates rates-json)))))

(defn- routes
  []
  (compojure/routes
    (GET "/" [] (layout/application "Coins" (->> (coins)
                                                 (coins-reader/enriched-coins)
                                                 (coin-html/conf->html))))
    (route/not-found "404")))

(def ^:private ring-defaults
  "Konfiguration für Session-Management, Security, statische Ressourcen, ..."
  (-> ring/api-defaults
      (assoc :static {:resources "public"})))

(def handler
  (-> (routes)
      (ring/wrap-defaults ring-defaults)))