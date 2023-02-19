(defproject {{name}} "0.1.0"
  :description "Clerk + your cool project!"
  :url "{{url}}"
  :dependencies [[metosin/compojure-api "2.0.0-alpha31"]
                 [org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [io.github.nextjournal/clerk "0.12.707"]]
  :ring {:handler server/app
         :init server/init}
  :main ^:skip-aot server
  :uberjar-name "{{name}}.jar"
  :aliases {"make-docs" ["run" "-m" "docs"]
            "start-clerk" ["run" "-m" "clerk"]}
  :profiles {:dev {:dependencies
                   [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins
                   [[lein-ring "0.12.5"]]}
             :uberjar {:aot :all
                       :omit-source true}}
  :repl-options {;; Defaults to 30000 (30 seconds)
                 :timeout 120000}
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"})
