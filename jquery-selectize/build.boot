;#!/usr/bin/env boot

;#tailrecursion.boot.core/version "2.5.0"

(load-file "../build.util.clj")
(require '[build.util :as build])

(set-env!
  :dependencies (build/deps)
  :src-paths    #{"src"}
  :out-path     "resources/public"
)

;(add-sync! (get-env :out-path) #{"resources/assets"})

(require '[tailrecursion.hoplon.boot      :refer :all]
         '[tailrecursion.boot.core        :as    boot]
         '[tailrecursion.boot.task.ring   :refer [dev-server]]
         '[tailrecursion.boot.task.notify :refer [hear]])

(deftask dev
  "Build foop for development."
  []
  (comp (watch) (hear) (hoplon {:prerender false}) (dev-server)))

(deftask production
  "Build foop for production."
  []
  (hoplon {:optimizations :advanced}))
