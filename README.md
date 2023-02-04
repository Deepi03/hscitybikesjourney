# helsinkiCityBikeJourney

This application does the following
 * Import data from csv file to db during application bootstrap
 * API endpoints for listing city bike journeys with details of each journey
 * API endpoint for listing city bike stations with details

## Prerequisite
Following tools required,
 * Java 8 or greater
 * Maven 3
 * Mongodb atlas(singIn mongodb atlas `` https://www.mongodb.com/atlas/database `` and provide connection credentials in application.properties)

 For example (application.properties) : 
 ``
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.qowburv.mongodb.net/?retryWrites=true&w=majority
spring.data.mongodb.database=<databasesname>
``


  ## API 
  ### Build and Run
``
mvn spring-boot:run
``

### Test

``
mvn test 
``
### Using postman

Method: 
 ``
 GET
``

URL:

 ``
 http://localhost:8080/api/journeys
 ``

  for Pagination(optional) :
``
http://localhost:8080/api/journeys?page=1&size=2
``

Response:

``
{
    "content": [
        {
            "departureTime": "2021-05-31T23:56:23",
            "returnTime": "2021-06-01T00:29:58",
            "departureStationId": "004",
            "departureStation": "Viiskulma",
            "returnStationId": "065",
            "returnStation": "Hernesaarenranta",
            "coveredDistance": 4318,
            "duration": 2009
        },
        {
            "departureTime": "2021-05-31T23:56:11",
            "returnTime": "2021-06-01T00:02:02",
            "departureStationId": "004",
            "departureStation": "Viiskulma",
            "returnStationId": "065",
            "returnStation": "Hernesaarenranta",
            "coveredDistance": 1400,
            "duration": 350
        },
        {
            "departureTime": "2021-05-31T23:54:48",
            "returnTime": "2021-06-01T00:00:57",
            "departureStationId": "292",
            "departureStation": "Koskelan varikko",
            "returnStationId": "133",
            "returnStation": "Paavalinpuisto",
            "coveredDistance": 1713,
            "duration": 366
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 3,
        "pageNumber": 1,
        "pageSize": 3,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 33,
    "totalElements": 98,
    "size": 3,
    "number": 1,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": false,
    "numberOfElements": 3,
    "empty": false
}
``

Method: 
 ``
 GET
``

URL:

 ``
 http://localhost:8080/api/stations
 ``

 for Pagination(optional) :
``
http://localhost:8080/api/stations?page=1&size=2
``

Response:
``
{
    "content": [
        {
            "station": "Kauppakorkeakoulu",
            "numberOfDepartures": 1,
            "numberOfReturns": 0
        },
        {
            "station": "Kaisaniemenpuisto",
            "numberOfDepartures": 1,
            "numberOfReturns": 0
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 2,
        "pageNumber": 1,
        "pageSize": 2,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 61,
    "totalElements": 121,
    "size": 2,
    "number": 1,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": false,
    "numberOfElements": 2,
    "empty": false
}
``

Method: 
 ``
 GET
``

URL:

 ``
 http://localhost:8080/api/stations/search?text=Kaisaniemenpuisto
 ``

Response:
``
[
    {
        "station": "Kaisaniemenpuisto",
        "numberOfDepartures": 1,
        "numberOfReturns": 0
    }
]
``
## Technology choosen
* Spring-boot 2.7
* git
* lombok 1.18
* junit


## TODO
* Dockerization
* More Unit tests
* E2E Test
* Additional features for Single station view

