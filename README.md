
# Dictionary Search & Auto Suggestion System

## 1. Explanation of the Data Structures Used

### HashMap<String, Integer>

The project uses a `HashMap<String, Integer>` named `dictionary`.

* Key → Stores the word
* Value → Stores the frequency of the word

Example:

```text id="1m3qj7"
apple -> 3
apply -> 2
banana -> 1
```

### Why HashMap?

* Fast insertion of words
* Fast word search
* Easy frequency update
* Efficient for backend operations

---

### ArrayList<String>

An `ArrayList` is used in the suggestion API to:

* Store words matching the given prefix
* Sort the suggestions before returning them

---

# 2. Overview of the Logic and Approach

## Add Word API

Endpoint:

```text id="6v8eyh"
POST /demo/add/{word}
```

Logic:

* Convert word to lowercase
* Check if word already exists
* If exists → return `"already exists"`
* Else:

  * Add word into HashMap
  * Initialize frequency as `1`

---

## Search Word API

Endpoint:

```text id="8hhxlp"
GET /demo/search/{word}
```

Logic:

* Convert word to lowercase
* Check if word exists in HashMap
* If found:

  * Increase frequency by 1
  * Return `"FOUND"`
* Else:

  * Return `"NOT FOUND"`

---

## Prefix Suggestion API

Endpoint:

```text id="m58y3f"
GET /demo/suggest?prefix=app&k=3
```

Logic:

* Traverse all words in HashMap
* Select words starting with given prefix using:

```java id="0sjqvn"
startsWith(prefix)
```

* Store matching words in ArrayList
* Sort based on:

  1. Higher frequency first
  2. Alphabetical order
* Return maximum `k` suggestions

---

# 3. Steps to Run and Test the Code

## Run the Project

1. Open project in IntelliJ IDEA
2. Open:

```text id="q88fwu"
DemoApplication.java
```

3. Run the application

Server starts on:

```text id="uxn5so"
http://localhost:8080
```

---

# Test APIs Using Postman

## Add Word

Method:

```text id="b7u5p5"
POST
```

URL:

```text id="wwt8cv"
http://localhost:8080/demo/add/apple
```

Response:

```text id="5w0m4m"
Word Added
```

---

## Search Word

Method:

```text id="mvqjk7"
GET
```

URL:

```text id="v4xfqf"
http://localhost:8080/demo/search/apple
```

Response:

```text id="l4mu0l"
FOUND
```

---

## Suggest Words

Method:

```text id="h8ejxq"
GET
```

URL:

```text id="9e3z5h"
http://localhost:8080/demo/suggest?prefix=app&k=3
```

Response:

```json id="4g3o3j"
[
  "apple"
]
```

---

# 4. Important Technical Assumptions

* Words are treated as case-insensitive
* Data is stored in-memory using HashMap
* No database is used
* Duplicate words are not allowed
* Suggestions are sorted by:
  1. Frequency
  2. Lexicographical order
* Backend-only implementation
* No frontend/UI included
* Spring Boot is used for REST API creation as I have used Java as my main prgramming language.
