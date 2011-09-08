(ns gsfd.watchfolder
  (:use [gsfd core files]))



(defn todo-lines [coll] (seq-filters [#"TO-DO" #"to-do" #"TO-DO" #"TD"] coll))

(defn mapa-filtrado [f mapa] {(key mapa) (f (val mapa))})

(defn watchfolder-todos []
  (let [all (all-watchfolder)]
        (map #(mapa-filtrado todo-lines %) all)))