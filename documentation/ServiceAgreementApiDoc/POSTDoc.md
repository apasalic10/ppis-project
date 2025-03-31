**Add Service Agreement**
----
Adds a service agreement instance into the database

* **URL**

  /api/serviceAgreement

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"Created successfully"`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Service Agreement has missing parameters" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Service Agreement has missing status" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "End date cannot be greater than start date" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Start date cannot be greater than creation date" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Service Agreement has missing terms" }`

* **Sample Call:**

  $.ajax({
  url: "/api/serviceAgreement",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });