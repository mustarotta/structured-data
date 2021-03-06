(ns structured-data)

(defn do-a-thing [x]
  (let[xx (+ x x)]
    (Math/pow xx xx)
  )
)

(defn spiff [v]
  (+ (get v 0) (get v 2))
)

(defn cutify [v]
  (conj v "<3")
)


(defn spiff-destructuring [v]
  (let [[a b c] v]
    (+ a c))
)

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1))
)

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- y2 y1))
)


(defn square? [rectangle]
    (== (width rectangle) (height rectangle))
)

(defn area [rectangle]
  (* (width rectangle) (height rectangle))
)

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (and
       (<= x1 (get point 0) x2)
       (<= y1 (get point 1) y2)
    )
  )
)

(defn contains-rectangle? [outer inner]
  (let [[[x1 y1] [x2 y2]] inner
        a (point x1 y1)
        b (point x2 y2)]
    (and (contains-point? outer a) (contains-point? outer b)))
)

(defn title-length [book]
  (count (:title book))
)

(defn author-count [book]
  (count (:authors book))
)

(defn multiple-authors? [book]
  (< 1 (author-count book))
)

(defn add-author [book new-author]
  (assoc book :authors
    (conj (:authors book) new-author)
    )
)

(defn alive? [author]
  (not (contains? author :death-year))
)



(defn element-lengths [collection]
  (map count collection)
)

(defn second-elements [collection]
  (let [b (fn[x] (get x 1))]
    (map b collection))
)

(defn titles [books]
  (map :title books)
)

(defn monotonic? [a-seq]
  (if (apply <= a-seq)
    true
    (if (apply >= a-seq)
    true
    false)
  )
)

(defn stars [n]
  (apply str (repeat n "*"))
)

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)
  )
)

(defn contains-duplicates? [a-seq]
  (let[a (count a-seq)
       b (count(set a-seq))]
    (if(== a b)
      false
      true
    ))
)

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book)))
)

(defn has-author? [book author]
  (contains? (:authors book) author)
)


(defn authors [books]
  (apply clojure.set/union (map :authors books))
)

(defn all-author-names [books]
  (set (map :name (authors books)))
)

(defn author->string [author]
  (let [n (:name author)
        death (if (contains? author :death-year)
               (str (:death-year author))
               ""
           )
        birth (if (contains? author :birth-year)
             (str (:birth-year author))
             ""
           )]
          (if (> (count death) 0)
            (str n " (" birth " - " death ")")
            (if (> (count birth) 0)
              (str n " (" birth " - )")
              (str n))
          )
  )
)

(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))
  )
)

(defn book->string [book]
  (str (:title book) ", written by " (authors->string (:authors book)))
)

(defn books->string [books]
  (cond
     (< (count books) 1)
       (str "No books.")
     (= (count books) 1)
       (str "1 book. " (apply book->string books) ".")
     :else
      (str (count books) " books. " (apply str (interpose ". " (map book->string books))) "."
      )
   )
)

(defn books-by-author [author books]
    (filter (fn [book] (has-author? book author)) books)
)

(defn author-by-name [name authors]
  (first (filter (fn [author] (= name (:name author))) authors))
)

(defn living-authors [authors]
  (filter alive? authors)
)

(defn has-a-living-author? [book]
  (not
     (empty? (filter alive? (:authors book)))
  )
)

(defn books-by-living-authors [books]
  (filter has-a-living-author? books)
)

; %________%







