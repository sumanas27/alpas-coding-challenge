# alpas-coding-challenge

The Price API service has the responsibility is to record
price changes and calculate average price for the last 30 seconds. 

### Feature(s)
- Record Price : To record price with current timestamp this endpoint `/api/v1/prices` is developed.
- Get average price : To get the average price recorded for the last 30 seconds this endpoint `/api/v1/average-prices` is developed.
- Get Prices : To show all the prices recorded with timestamp  this endpoint `/api/v1/` is developed. [Extra]

More details can be found on Swagger API documentation via /swagger endpoint on port 8080.

### Prerequisite 
- Docker daemon is installed and running

### Running the service
The following commands are useful if the service has to run.
To ensure smooth build and running processes, a Dockerfile is created in the source code directory.

To build this application following command is necessary:

```
docker build -t alpas-coding-challenge .
``` 
To run this application following command is necessary

```
docker run -p 8080:8080 alpas-coding-challenge
```
To ensure that docker image is running, following command will give the overview of the image.

```
docker ps
```

### Swagger Documentation

Swagger UI: http://localhost:8080/swagger-ui/index.html#/