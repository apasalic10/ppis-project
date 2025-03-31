**Update review**
----
Returns json data about a review.

* **URL**

  /api/review/:id

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
    "id": 1,
    "reviewerId": "teacher_123",
    "revieweeId": "student_456",
    "rating": 1,
    "comment": "Excellent student, very engaged!",
    "serviceAgreementStatus": "CANCELLED",
    "sessionStatus": "COMPLETED"
}`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Review with ID " + id + " not found"}`

  OR

    * **Code:** 400 BAD_REQUEST <br />
      **Content:** `"Invalid field: " + key`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/review/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```