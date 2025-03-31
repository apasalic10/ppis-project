**Show Review**
----
Returns json data about a review.

* **URL**

  /api/review/:id

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
    "reviewerId": "teacher_123",
    "revieweeId": "student_456",
    "rating": 5,
    "comment": "Excellent student, very engaged!",
    "serviceAgreementStatus": "CANCELLED",
    "sessionStatus": "COMPLETED"
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Review not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/review/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  });
 