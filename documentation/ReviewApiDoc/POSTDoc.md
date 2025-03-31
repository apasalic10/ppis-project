**Add review**
----
Adds a review instance into the database

* **URL**

  /api/review

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"Review created successfully"`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Service Agreement not found" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Session not found" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Review requires has missing parameters" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Review has missing reviewer id" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Review has missing reviewee id" }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Rating can't be negative" }`


OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invalid Submission Date" }`


* **Sample Call:**

  $.ajax({
  url: "/api/review",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });