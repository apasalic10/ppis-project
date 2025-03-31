**Show Learning Progress**
----
Returns json data about learning progress.

* **URL**

  /api/learningProgress/:id

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
    "learningProgressId": 1,
    "studentId": "student_456",
    "skillName": "Mathematics",
    "currentLevel": 3,
    "serviceAgreementStatus": "CANCELLED"
}`

* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Learning progress not found ID: " + id }`


* **Sample Call:**

  $.ajax({
  url: "/api/learningProgress/1",
  dataType: "json",
  type : "GET",
  success : function(r) {
  console.log(r);
  }
  }); 