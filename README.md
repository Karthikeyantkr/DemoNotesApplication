# Demo Note server application

### Description
The Note server application allows basic CRUD operations to be performed via REST end points.
Application uses a persistent database which means upon shutting down server all the data in db is lost.

### Steps to run the application
1. Clone the project locally and make sure java8 is set in your system.
	Reference:
	To install java8: https://mkyong.com/java/how-to-install-java-on-mac-osx/#homebrew-install-java-8-on-macos
	To install gradle: https://gradle.org/install/
2. Go to the project root directory.
3. Run ./gradlew clean build to make sure the application builds successfully.
4. Run ./gradlew clean bootRun to start the application 

Note: The application runs in port 8080. 
To configure a different add 'server.port = 8081' in application.properties

### End points in the application with example request

Get all notes:
curl --location --request GET 'http://localhost:8080/api/notes'

Find a note by id:
curl --location --request GET 'http://localhost:8080/api/notes/1'

Create a note:
curl --location --request POST 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "content" : "note1"
}'

Create one or more notes:
curl --location --request POST 'http://localhost:8080/api/notes/bulk' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "id": 5,
        "content": "note5"
    },
    {
        "id": 6,
        "content": "note6"
    }
]'

Search note based on keyword: 
curl --location --request GET 'http://localhost:8080/api/notes?keyword=note' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "content" : "my first note!"
}'

Edit a note:
curl --location --request PUT 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "content" : "note_updated"
}'

Edit one or more notes:
curl --location --request PUT 'http://localhost:8080/api/notes/bulk' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "id": 1,
        "content": "note1_updated"
    },
    {
        "id": 2,
        "content": "note2_updated"
    }
]'

Delete a note by id:
curl --location --request DELETE 'http://localhost:8080/api/notes/1'

Delete a note by entity:
curl --location --request DELETE 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 6,
    "content": "note6"
}'


