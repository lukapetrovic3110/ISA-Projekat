                                         Internet software architecture Project 2020/2021
 
| Role     |              TEAM 39      |
|-------------------|------------------|
| Student 1 | Milica Lukić   RA17/2017 |
| Student 2 | Luka Petrović  RA25/2017 |
| Student 3 | Zorana Lazić   RA53/2017 |
| Student 4 | Ivana Gavrić   RA160/2017|

## Git rules

- Commit message:

    [feat/refactor/fix] + ": " + description
    
    e.g. "feat: Added patient registration"
    
- Branch name:

    "feat/refactor" + "/" + description
    
    e.g. "feat/pharmacy-profile" 

## Technologies

- Spring Boot
- Vue.js
- Node.js
- PostgreSQL

## Database - PostgreSQL
- The database operates on the port by default `5432`
- After starting pgAdmin, it is necessary to create a database named jpa
- Datasource username : postgres, password : root
- There is a data-postgres.sql script in the resources folder that runs automatically when you start a back-end project
- This is set in application.properties - spring.jpa.hibernate.ddl-auto = create-drop

## Steps to take when starting a back-end project in an Eclipse development environment
- Import ISABackEnd project into workspace: Import -> Maven -> Existing Maven Project
- Then install all the dependencies from the pom.xml file
- Finally, right-click on the project -> Run as -> Java Application / Spring Boot app (if the STS plugin from the Eclipse marketplace is installed)
- The back-end will be launched on port `8091`

## Steps to take when starting a front-end project in a Visual Studio Code environment
- You must install node.js
- Open your Visual Studio Code and import the ISAFrontEnd folder
- Open a terminal in the Visual Studio Code and then type the commands:
```sh
                                $ npm install
                                $ npm run serve
```
- The front-end will be launched on port `8080`
- If your port is busy, follow the instructions below:
- Run Cmd.exe ( Command Prompt ) as administrator
- Then type the commands:
```sh
                                $ netstat -ano | findstr :8080
                                $ taskkill /PID <PID> /F
```
- Enter the number on which he is listening instead of `<PID>`
