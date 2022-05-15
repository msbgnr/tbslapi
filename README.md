# BiggerGames Case Study

Hello, this is an Basketball League API for Bigger Games.

### Project Requirements

* Install JDK (use 8 or later version) (https://www.oracle.com/java/technologies/downloads/)
* Install Maven (https://maven.apache.org/install.html)
* Install Docker (for local Redis) (https://docs.docker.com/get-docker/)

### How To Run

In terminal run below docker command and Redis will run on local port 6379

* cd PathToRootDirectory
* docker-compose up -d

After Redis ready run project with Maven commands

* cd PathToRootDirectory
* mvn clean install
* java -jar target/tbsl-api-1.0.0.jar

Once the project starts running you can access the Swagger UI's homepage 
and you can reach API details from there.

### Swagger UI:
* Tool for testing APIs,documentation for Models and APIs
  http://localhost:8080/api/swagger-ui.html

Also curl or Postman can be used for testing in local.

Example curl command for MatchScore:
curl -X POST "http://localhost:8080/api/v1/match-score" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"home\": \"PINAR KARŞIYAKA\", \"guest\": \"FRUTTİ EXTRA BURSASPOR\", \"hScore\": 98, \"gScore\": 92}"

### APIs

* MatchScore http://localhost:8080/api/v1/match-score

Example JSON Request for MatchScore:
    {
        "home": "PINAR KARŞIYAKA",
        "guest": "FRUTTİ EXTRA BURSASPOR",
        "hScore": 98,
        "gScore": 92
    }

* Team http://localhost:8080/api/v1/team/{teamName}
* Team Standings http://localhost:8080/api/v1/team-standings


Thank you.

`Musab GÜNER <msbgnr@gmail.com>`
