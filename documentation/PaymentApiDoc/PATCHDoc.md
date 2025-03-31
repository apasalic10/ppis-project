**Update Payment**
----
Returns json data about a payment.

* **URL**

  /api/payment/:id

* **Method:**

  `PATCH`

*  **URL Params**

   **Required:**

   `id=[integer]`

* **Data Params**

  JSON body

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `{
    "paymentId": 1,
    "amount": 150.75,
    "currency": "USD",
    "paymentDate": "2025-03-31T21:03:42.500+00:00",
    "status": "payed",
    "serviceAgreementStatus": "CANCELLED",
    "sessionStatus": "COMPLETED"
}`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Payment with ID " + id + " not found"}`

  OR

    * **Code:** 400 BAD_REQUEST <br />
      **Content:** `"Invalid field: " + key`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/payment/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```