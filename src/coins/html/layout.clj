(ns coins.html.layout
  (:use
    [hiccup.core]
    [hiccup.page]))

(defn head
  [title]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:content "width=device-width, initial-scale=1, shrink-to-fit=no"
           :name "viewport"}]
   [:meta {:content ""
           :name "description"}]
   [:meta {:content "Timo Linde"
           :name "author"}]
   [:title title]
   "<!-- Bootstrap core CSS -->"
   [:link {:crossorigin "anonymous"
     :integrity "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
     :rel "stylesheet"
     :href "https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css"}]
   [:link {:rel "stylesheet", :href "https://getbootstrap.com/docs/4.3/examples/cover/cover.css"}]
   [:style ".cover-container {max-width: 80em;}"]]
  )

(defn nav
  []
  [:nav.nav.nav-masthead.justify-content-center.d-none
   [:a.nav-link.active {:href "#"} "Home"]
   [:a.nav-link {:href "#"} "Features"]
   [:a.nav-link {:href "#"} "Contact"]]
  )

(defn footer
  []
  [:footer.mastfoot.mt-auto
   [:div.inner
    [:p
     "&copy; D3K mit "
     [:a {:href "https://getbootstrap.com/"} "Bootstrap"]
     ", von "
     [:a {:href "https://timo-linde.de"} "Timo Linde"]
     "."]]]
  )

(defn application
  [title content]
  (html5 {:lang "en"}
          (head title)
          [:body.text-center
           [:div.cover-container.d-flex.w-100.h-100.p-3.mx-auto.flex-column
            [:header.masthead.mb-auto
             [:div.inner
              [:h3.masthead-brand title]
              (nav)]]
            [:main.inner.cover
             {:role "main"}
             [:h1.cover-heading title]
             [:p.lead
              "Mein Münzrechner"]
             [:p.lead [:a.btn.btn-lg.btn-secondary.d-none {:href "#"} "Button"]
              content]]
            (footer)
            ]])
  )