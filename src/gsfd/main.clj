(ns gsfd.main (:use '[gsfd core files watchfolder code]) 
	(:gen-class))

(defn -main [] (print-all (take-wonils 10 (main-data))))