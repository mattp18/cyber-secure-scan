# Cyber Secure Scan

An application that scans files for malware and generates report if malware was found.

### Additional commands to run in the root repository

## Getting Started

### if you have docker installed, then run this command

- `yarn run-docker` # Starts the docker services defined in docker-compose.yml


## Notes

- The Default profiles for the application are **development**, **test** and **production** to test out production
  functionalities.
    - Datasource must be provided for production profile for the application to run.

\*NB: Windows users must use **gradlew** instead of **./gradlew\*** in **API**

- Start Spring Boot application using on linux/unix - **./gradlew bootRun**
- Run unit tests using - **./gradlew test**
- Run all tests using - **./gradlew testAll**

- Access API application on _<http://localhost:8080/>_
- Access Frontend application on _<http://localhost:3000/>_

    - Credentials for Minio on docker are

```bash
username: root
password: password
```


## Running on Docker (Assuming docker is installed)

- in the root directory (et-transit) where the docker-compose.yml is, simply run the command - **docker-compose up**

## Stop running application on Docker

- in the directory where docker-compose.yml file resides, simply run the command - **docker-compose down**

## Deleting all containers and images created by the application

\*NB: This command will remove **ALL** containers and images (Assuming no other containers and images already exist) **Caution\***

```bash
docker system prune -a --volumes
```
## Steps
1. Run docker in the root of the project using the following command
```bash
docker-compose up 
```
2. To ensure the backend application builds without any errors
```bash
./gradlew build 
```
3. Run the application
```bash
./gradlew bootRun
```
4. Navigate to cyber-secure-scan-frontend
```bash
cd cyber-secure-scan-frontend 
```
5. To start the frontend server
```bash
npm run dev 
```
6. Navigate to http://localhost:3000 on the browser
![Screenshot](https://i.imgur.com/KhcnY1C.png)
7. To view report, navigate to http://localhost:9000 on the browser
8. Report can be downloaded from MinIO upload reports bucket



Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426)
for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- **Matthew Puentes** - _Initial work_
- **Jessica Puentes**
