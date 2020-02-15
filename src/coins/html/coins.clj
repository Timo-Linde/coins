(ns coins.html.coins
  (:require [coins.reader :as reader]
            [clojure.pprint :as pprint]
            [common.converter :as converter]
            [common.formatter :as formatter]))

(defn coin->html
  [{:keys [euro-currency-rate] :as _conf} index {:keys [name material ratingColor value amount amountValue] :as coin}]
  [:tr
   [:th {:scope "row"} (inc index)]
   [:td name]
   [:td material]
   [:td
    {:class (str "bg-" ratingColor)
     :style (str "background-color: " ratingColor)}]
   [:td (formatter/format-oz (reader/coin-weight-in-oz coin))]
   [:td amount]
   [:td (formatter/format-euro (converter/usd->euro euro-currency-rate value))]
   [:td (formatter/format-euro (converter/usd->euro euro-currency-rate amountValue))]])

(defn coins->html
  [{:keys [coins] :as conf}]
  [:table.table.table-dark
   [:thead
    [:tr
     [:th {:scope "col"} "#"]
     [:th {:scope "col"} "Name"]
     [:th {:scope "col"} "Material"]
     [:th {:scope "col"} "Rating"]
     [:th {:scope "col"} "Gewicht"]
     [:th {:scope "col"} "Menge"]
     [:th {:scope "col"} "Wert einzeln"]
     [:th {:scope "col"} "Wert alle"]]]
   [:tbody
    (map-indexed (partial coin->html conf) coins)
    ]])

(defn conf->html
  [{:keys [euro-currency-rate gold-price silver-price] :as conf}]
  (list [:div.text-left
          [:span "1€ = " euro-currency-rate "$"]
          [:br]
          [:span "Gold Preis: " (formatter/format-euro (converter/usd->euro euro-currency-rate gold-price)) " / " gold-price "$"]
          [:br]
          [:span "Silber Preis: " (formatter/format-euro (converter/usd->euro euro-currency-rate silver-price)) " / " silver-price "$"]
          ]
         [:br]
         (coins->html conf)))