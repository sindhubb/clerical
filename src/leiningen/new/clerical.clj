(ns leiningen.new.clerical
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main])
  (:import [java.nio.file Files FileSystems]
           [java.nio.file.attribute FileAttribute]))

(def render (tmpl/renderer "clerical"))

(defn strings [& rest] (into-array String rest))
(defn attrs [& rest] (into-array FileAttribute rest))

(defn create-sim-link [dir link target]
  (let [fs (FileSystems/getDefault)
        link-path (.getPath fs dir (strings link))
        target-path (.getPath fs dir (strings target))]
    (Files/createSymbolicLink link-path target-path (attrs))))

(defn touch [dir file]
  (let [fs (FileSystems/getDefault)
        file-path (.getPath fs dir (strings file))]
    (Files/createDirectories (.getParent file-path) (attrs))
    (Files/createFile file-path (attrs))))

(defn clerical
  "Function to generate a project with Clerk added."
  [name]
  (let [sanitized (tmpl/name-to-path name)
        data {:name name
              :sanitized sanitized
              :url (str "https://github.com/your-username/" name)}]
    (main/info "Generating a fresh project with Clerk added!")
    (tmpl/->files data
                  [".gitignore" (render "gitignore" data)]
                  ["src/{{sanitized}}/sample.clj" (render "src/sample/sample.clj" data)]
                  ["src/server.clj" (render "src/server.clj" data)]
                  ["src/docs.clj" (render "src/docs.clj" data)]
                  ["src/clerk.clj" (render "src/clerk.clj" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["README.md" (render "README.md" data)])
    (touch sanitized "resources/docs/.nojekyll")
    (create-sim-link sanitized "docs" "resources/docs")
    ()))
