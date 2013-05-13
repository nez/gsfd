(ns gsfd.core)

(def *le-pipeline* "/Users/David/Desktop/lepipeline.txt")

(def *le-watchfolder* "/f")

(defn take-wonils [n coll] (remove nil? (take n coll)))

(defn seq-filter [re coll] (filter #(re-seq re %) coll))

(defn multisec [res] (fn [elem] (seq (mapcat #(re-seq % elem) res))))

(defn seq-filters [res coll] (filter #((multisec res) %) coll))

(defn unchecked [coll] (filter #(not (re-seq #"check" %)) coll))
(defn no-comments [coll] (filter #(not (re-seq #"\*\*" %)) coll))

(defn checked [coll] (seq-filter #"check" coll))
(defn half [coll] (seq-filter #"1/2" coll))
 
;tasks with lines so you can delete one.

(defn print-all [coll] (loop [coll (remove nil? coll)] 
                         (when (not (empty? coll)) (println (first coll))
                                    (recur (rest coll)))))


;testandebug
(def res1 [#"TO-DO" #"to-do" #"TO-DO" #"TD" #"TODO:"])
