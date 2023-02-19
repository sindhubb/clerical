(ns docs
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :refer [file]])
  (:gen-class))

(def inpath "src/{{name}}")

(defn files
  []
  (->> (file inpath)
       (file-seq)
       (map #(.getPath %))
       (filter #(.endsWith % ".clj"))))

(defn -main
  []
  (clerk/build! {:paths (files) :out-path "resources/docs"}))
