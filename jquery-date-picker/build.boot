(set-env!
  :dependencies '[[adzerk/boot-cljs          "1.7.48-4"]
                  [adzerk/boot-cljs-repl     "0.1.9"]
                  [adzerk/boot-reload        "0.3.2"]
                  [cljsjs/boot-cljsjs        "0.5.0"]
                  [hoplon/boot-hoplon        "0.1.10"]
                  [hoplon/hoplon             "6.0.0-alpha10"]
                  [hoplon/jquery-daterange-picker "0.0.5-0"]
                  [hoplon/twitter-bootstrap  "0.2.0"]
                  [org.clojure/clojure       "1.7.0"]
                  [org.clojure/clojurescript "1.7.122"]
                  [tailrecursion/boot-jetty  "0.1.0"]]
  :source-paths   #{"src"}
  :resource-paths #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-cljs-repl    :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload       :refer [reload]]
  '[cljsjs.boot-cljsjs       :refer [from-cljsjs]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build jquery-date-picker for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (from-cljsjs)
    (sift :to-resource #{#"jquery-daterange-picker.inc.css"})
    (serve :port 8000)))

(deftask prod
  "Build jquery-date-picker for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))
