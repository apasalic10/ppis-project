**Update Learning Progress**
----
Returns json data about learning progress.

* **URL**

  /api/learningProgress/:id

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
    "learningProgressId": 1,
    "studentId": "student_456",
    "skillName": "Mathematics",
    "currentLevel": 1,
    "serviceAgreementStatus": "CANCELLED"
}`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Learning progress with ID " + id + " not found"}`

  OR

    * **Code:** 400 BAD_REQUEST <br />
      **Content:** `"Invalid field: " + key`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/users/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```