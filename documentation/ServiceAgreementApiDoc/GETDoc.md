**Show Service Agreement**
----
Returns json data about a service agreement.

* **URL**

  /api/serviceAgreement/:id

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
    "serviceAgreementId": 1,
    "teacherId": "teacher_123",
    "studentId": "student_456",
    "startDate": "2025-03-31T21:03:42.408+00:00",
    "endDate": "2025-04-01T21:03:42.408+00:00",
    "terms": "Standard agreement terms",
    "invoiceNumber": "INV-001",
    "currentLevel": 3,
    "amount": 150.75,
    "sessionStatuses": [
        "COMPLETED",
        "PENDING"
    ],
    "ratings": 4
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Service agreement not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/serviceAgreement/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  });
 