(ns coins.rates
  (:require [clojure.edn :as edn]
            [clojure.data.json :as json]
            [common.converter :as converter]))

;; Rates from http://goldpricez.com/about/api

(defn- parse-rates-json
  [json]
  (-> json
      (json/read-str)
      (json/read-str :key-fn keyword)))

(defn- knead-rates
  [{:keys [usd_to_eur ounce_price_usd] :as _parsed-rates}]
  {:euro-currency-rate (-> usd_to_eur
                           (edn/read-string)
                           (converter/switch-rate))
   :gold-price (-> ounce_price_usd
                   (edn/read-string))
   :silver-price 14.49})

(defn json->rates
  [json]
  (-> json
      (parse-rates-json)
      (knead-rates)))
