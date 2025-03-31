**Show Payment**
----
Returns json data about a payment.

* **URL**

  /api/payment/:id

* **Method:**

  `GET`

*  **URL Params**


   **Required:**

   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `{
    "paymentId": 1,
    "amount": 150.75,
    "currency": "USD",
    "paymentDate": "2025-03-31T21:03:42.500+00:00",
    "status": "COMPLETED",
    "serviceAgreementStatus": "CANCELLED",
    "sessionStatus": "COMPLETED"
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Payment not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/payment/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  });
 