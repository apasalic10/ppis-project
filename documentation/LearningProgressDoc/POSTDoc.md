**Add Learning Progress**
----
Adds a learning progress instance into the database

* **URL**

  /api/learningProgress

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"LearningProgress created"`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Service Agreement not found" }`

  OR
  * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Learning Progress has missing parameters" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Learning Progress has missing skill name" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Initial level should be less than current level" }`

* **Sample Call:**

  $.ajax({
  url: "/api/learningProgress",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });