**Show Session**
----
Returns json data about a session.

* **URL**

  /api/session/:id

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
    "id": 1,
    "startTime": "2025-03-31T21:03:42.494+00:00",
    "endTime": "2025-03-31T22:03:42.494+00:00",
    "status": "COMPLETED",
    "serviceAgreementStatus": "CANCELLED"
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Session not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/session/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  });
 