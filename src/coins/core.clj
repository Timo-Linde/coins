(ns coins.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [coins.handler :as coins-handler]
            ))

(defn -main
  "This is our app's entry point"
  [& args]
  (let [port 1337]
    (server/run-server #'coins-handler/new-handler {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))