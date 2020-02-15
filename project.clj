(defproject coins "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [hiccup "1.0.5"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 ]
  :main coins.core
  :repl-options {:init-ns coins.core})
