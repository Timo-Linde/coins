(ns coins.handler
  (:require [compojure.core :as compojure :refer [GET POST]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [ring.middleware.defaults :as ring]
            [coins.html.coins :as coin-html]
            [coins.reader :as coins]
            [coins.html.layout :as layout]
            [clojure.data.json :as json]))
;; https://data-asg.goldprice.org/dbXRates/EUR
(def coins {
            :coins              ((json/read-str (slurp "https://timo-linde.de/coins.json") :key-fn keyword) :coins)
            :euro-currency-rate 1.1184
            :gold-price         1276.65
            :silver-price       14.49
            })

(defn- routes
  []
  (compojure/routes
    (GET "/" [] (layout/application "Coins" (->> coins
                                                 (coins/enriched-coins)
                                                 (coin-html/conf->html))))
    (route/not-found "404")))

(def ^:private ring-defaults
  "Konfiguration für Session-Management, Security, statische Ressourcen, ..."
  (-> ring/api-defaults
      (assoc :static {:resources "public"})))

(defn new-handler
  []
  (-> (routes)
      (ring/wrap-defaults ring-defaults)
      ))