**Show Invoice**
----
Returns json data about an invoice.

* **URL**

  /api/invoice/:id

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
    "invoiceId": 1,
    "invoiceNumber": "INV-001",
    "issueDate": "2025-03-31T21:03:42.471+00:00",
    "dueDate": "2025-04-05T21:03:42.471+00:00",
    "totalAmount": 150.75,
    "status": "PAID",
    "serviceAgreementStatus": "CANCELLED"
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Invoice not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/invoice/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  });
 