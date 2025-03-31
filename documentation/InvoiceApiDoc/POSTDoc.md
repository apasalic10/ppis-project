**Add Service Agreement**
----
Adds a service agreement instance into the database

* **URL**

  /api/invoice

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"Invoice created"`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invoice has missing parameters" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invoice has null or empty information" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invoice has missing invoice number" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invoice has missing status" }`

* **Sample Call:**

  $.ajax({
  url: "/api/invoice",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });