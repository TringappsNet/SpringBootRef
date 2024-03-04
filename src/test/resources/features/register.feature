Feature: Register a new user

  Scenario: To see all the users
    When I send a GET request to "/api/v1/registers"
    Then the system should get all the users successfully with a status code of 200


  Scenario: Register a new user with all details
    Given a new user with the following details:
      |id| username | password  | phoneNumber | email             | profile | csvData | verified |
      |2015 | Tharun   | Tharun@1234 | 987641574    | tharun@gmail.net | reader.result | Question | no      |
    When I send a POST request to "/api/v1/registers"
    Then the system should register the user successfully with a status code of 200

  Scenario: Update an existing user's verified status
    Given an existing user with the following details:
      | id   | username | password  | phoneNumber | email             | profile | csvData | verified |
      | 2953 | Anandth     | Anandth@1234 | 98768674    | anand@gmail.net | reader.result | Question | Yes      |
    When I send a PUT request to "/api/v1/registers/2953"
    Then the system should update the user's verified status successfully with a status code of 200

  Scenario: Delete an existing user
    Given an existing user details:
      | id   | username | password  | phoneNumber | email             | profile | csvData | verified |
      | 2906 | Hari     | Hari@1234 | 98765674    | harish@tringapps.com | reader.result | Question | null      |
    When I send a DELETE request to "/api/v1/registers/2906"
    Then the system should update the user's status successfully with a code of 200




