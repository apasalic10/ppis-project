**Add Session**
----
Adds a session instance into the database

* **URL**

  /api/session

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"Session created successfully"`

* **Error Response:**

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Session not found" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Session requires has missing parameters" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "End time cannot be greater than start time" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Session has missing status" }`


* **Sample Call:**

  $.ajax({
  url: "/api/session",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });