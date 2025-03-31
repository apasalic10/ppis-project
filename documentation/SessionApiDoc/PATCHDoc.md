**Update session**
----
Returns json data about a session.

* **URL**

  /api/session/:id

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
    "startTime": "2025-03-31T21:03:42.494+00:00",
    "endTime": "2025-03-31T22:03:42.494+00:00",
    "status": "UNCOMPLETED",
    "serviceAgreementStatus": "CANCELLED"
}`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{Error message: "Session with ID " + id + " not found"}`

  OR

    * **Code:** 400 BAD_REQUEST <br />
      **Content:** `"Invalid field: " + key`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/session/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```