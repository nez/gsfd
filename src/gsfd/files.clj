(ns gsfd.files 
  (:use gsfd.core)
  (:import [java.io File]))


(defn wo-hidden [coll] (filter #(not (re-seq #"/\." %)) coll))
(defn valid-format [coll] (filter #(re-seq #".clj" %) coll))

(defn read-lines [f] (re-seq #"[^\n]+" (slurp f)))

(defn main-data [] (no-comments (unchecked (read-lines *le-pipeline*))))

(defn dir-descendants [dir]
  (valid-format (wo-hidden (let [children (.listFiles (File. dir))]
    (lazy-cat
     (map (memfn getPath) (filter (memfn isFile) children))
     (mapcat dir-descendants
      (map (memfn getPath) (filter (memfn isDirectory) children))))))))

(defn all-folder [folder]
  (let [descendants (dir-descendants folder)]
    (apply merge (map #(hash-map (keyword %) (read-lines %)) descendants))))

(defn all-watchfolder "mapa, :filename file-lines" []
  (all-folder *le-watchfolder*))

