(ns todo.core
	(:gen-class))

(def *le-pipeline* "/Users/nex/Desktop/lepipeline.txt")
(defn read-lines [f] (re-seq #"[^\n]+" (slurp f)))

(defn seq-filter [re coll] (filter #(re-seq re %) coll))

(defn unchecked [coll] (filter #(not (re-seq #"check" %)) coll))
(defn no-comments [coll] (filter #(not (re-seq #"\*\*" %)) coll))

(defn checked [coll] (seq-filter #"check" coll))
(defn half [coll] (seq-filter #"1/2" coll))

(defn main-data [] (no-comments (unchecked (read-lines *le-pipeline*))))

(defn -main [] (loop [i 10 data (main-data)] (if (> i 0) (do (println (first data)) (recur (- i 1) (rest data))))))