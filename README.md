# Cyber Secure Scan

An application that scans files for malware and generates report if malware was found.

## Developer Setup

When initially setting up the project, install packages from the root of the repository to enable git hooks.

```bash
yarn deps # Install dependencies and configures hooks

yarn start # Starts up the application using docker. Docker must be running for this
```

While using the pre-commit hooks is highly encouraged, if you ever need to force a commit to go through without passing the pre-commit hooks, you can commit with the `--no-verify` flag.

### Additional commands to run in the root repository

## Getting Started

### if you have docker installed, then run this command

- `yarn run-docker` # Starts the docker services defined in docker-compose.yml

### If no docker

- Create

- Configure MinIO locally.

## Notes

- The Default profiles for the application are **development**, **test** and **production** to test out production
  functionalities.
    - Datasource must be provided for production profile for the application to run.

\*NB: Windows users must use **gradlew** instead of **./gradlew\*** in **API**

- Start Spring Boot application using on linux/unix - **./gradlew bootRun**
- Run unit tests using - **./gradlew test**
- Run integration tests using - **./gradlew integrationTest**
- Run all tests using - **./gradlew testAll**

- Run owasp dependency check - **./gradlew dependencyCheckAnalyze --info**
- Access API application on _<http://localhost:8080/>_
- Access Frontend application on _<http://localhost:3000/>_
- Access Swagger UI - **<http://localhost:8080/swagger-ui/index.html>**

    - Credentials for Minio on docker are

```bash
username: admin
password: password
```

```bash
Admin user with "ADMIN" role
username: admin
password: password

Second user with "USER" role
username: user
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

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426)
for details on our code of conduct, and the process for submitting pull requests to us.

## Credit

- **Template Used** - [Sakai](https://github.com/primefaces/sakai-react)

## Authors

- **Matthew Puentes** - _Initial work_
- **Jessica Puentes**
