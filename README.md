# BiggerGames Case Study

Hello, this is an Basketball League API for Bigger Games.

### Project Requirements

* Install JDK (use 8 or later version)
* Install Docker (for local Redis)

### How To Run

In terminal run below docker command and Redis will run on local port 6379

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

### APIs

* MatchScore http://localhost:8080/api/v1/match-score
* Team http://localhost:8080/api/v1/team/{teamName}
* Team Standings http://localhost:8080/api/v1/team-standings


Thank you.

`Musab GÜNER <msbgnr@gmail.com>`
