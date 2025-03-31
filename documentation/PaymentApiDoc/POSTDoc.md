**Add Payment**
----
Adds a payment instance into the database

* **URL**

  /api/payment

* **Method:**

  `POST`

*  **URL Params**


   **Required:**

   None


* **Data Params**

  JSON body

* **Success Response:**

  * **Code:** 200 <br />
  **Content:** `"Payment created"`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Service Agreement not found" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Session not found" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Invalid payment data" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Amount can't be negative" }`

  OR

    * **Code:** 404 NOT_FOUND <br />
      **Content:** `{Error message: "Payment has missing currency" }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Payment has missing status" }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Platform fee can't be negative" }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Teacher amount can't be negative" }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{Error message: "Payment has missing payment method" }`

* **Sample Call:**

  $.ajax({
  url: "/api/payment",
  dataType: "json",
  type : "POST",
  success : function(r) {
  console.log(r);
  }
  });