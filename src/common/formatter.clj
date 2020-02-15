(ns common.formatter)

(defn format-euro
  [euro]
  (format "%.2f€" (with-precision 2 :rounding CEILING euro)))

(defn format-oz
  [oz]
  (format "%.3f oz" (with-precision 3 :rounding CEILING oz)))