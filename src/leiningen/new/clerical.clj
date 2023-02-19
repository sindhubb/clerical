(ns leiningen.new.clerical
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "clerical"))

(defn clerical
  "Function to generate a project with Clerk added."
  [name]
  (let [data {:name name
              :sanitized (tmpl/name-to-path name)
              :url (str "https://github.com/your-username/" name)}]
    (main/info "Generating a fresh project with Clerk added!")
    (tmpl/->files data
                  ["resources/docs/.nojekyll" (render "resources/docs/.nojekyll")]
                  [".gitignore" (render ".gitignore" data)]
                  ["src/{{sanitized}}/sample.clj" (render "src/sample/sample.clj" data)]
                  ["src/server.clj" (render "src/server.clj" data)]
                  ["src/docs.clj" (render "src/docs.clj" data)]
                  ["src/clerk.clj" (render "src/clerk.clj" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["README.md" (render "README.md" data)])))
