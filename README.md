# alpas-coding-challenge

The Price Service Application has the responsibility is to record price changes and 
calculate average price for the last 30 seconds. 

### Feature(s)
- Record Price : To record price with current timestamp this endpoint `/api/v1/prices` is developed.
- Get average price : To get the average price recorded for the last 30 seconds this endpoint `/api/v1/average-prices` is developed.
- Get Prices : To show all the prices recorded with timestamp  this endpoint `/api/v1/` is developed. [Extra]

More details can be found on Swagger API documentation via /swagger endpoint on port 8080.

### Prerequisite 
- Docker daemon is installed and running

### Running the service

The application contains a build script `build.sh`

To ensure smooth build and running processes, a Dockerfile is created in the source code directory.

To build this application following command is necessary:

- Make it executable (only once):
```
chmod +x build.sh
``` 
- Run the build script:
```
./build.sh
```

### Swagger Documentation

Swagger UI: http://localhost:8080/swagger-ui/index.html#/