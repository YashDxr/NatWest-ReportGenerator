## NatWest Assignment Report Generator

## Description
This project is a report generation application developed as NatWest assignment. It leverages Spring Boot and MySQL to ingest input data, process it against reference data, and generate customized reports at the same time and also via scheduling.

## Features
- **CSV File injection for InputData and ReferenceData via JSP and POSTMAN API TESTING**
- **Schedule Report Generation based on your CHOICE of date and time also via JSP and POSTMAN API TESTING**
- **Report Generation**: Generates reports based on input and reference data.

## Technologies Used
- Java
- Spring Boot
- MySQL
- Junit5
- Maven

## Setup Instructions
1. **Clone the repository**
   ```bash
   git clone https://github.com/YashDxr/NatWest-ReportGenerator
   cd NatWest-Assignment-ReportGenerator
   ```

2. **Set up MySQL Database**
   - Open application.properties and enter your MYSQL DATABASENAME, USERNAME, PASSWORD

3. **Run the Application**

4. **Access the Application**
   - The application will be accessible at `http://localhost:8080` and if you want to change default port use.
   ```bash
   server.port=8888
   ```
   in application.properties.

## API Endpoints

1. **POST: /api/files/upload**
   - Uploads two CSV files (`inputFile`, `ReferenceFile`), saves the data, and generates a report.

2. **GET: /api/files/generatereport**
   - Generates reports based on data from the InputTable and ReferenceTable.

3. **GET: /api/files/getreport**
   - Retrieves existing reports from the OutputTable.

4. **GET: /api/reports/generate**
   - Generates a report when a file is uploaded or directly called.

5. **POST: /api/reports/schedule**
   - Schedule report generation based on local datetime value.

6. **GET: /report**
   - Retrieves scheduled report data when the scheduled task is executed.

7. **GET: /api/reports/referencedata**
   - Retrieves all reference data from MySQL.

8. **GET: /api/reports/inputdata**
   - Retrieves all input data from MySQL.


## Testing API endpoints
POSTMAN LINK IS PROVIDED IN REPOSITORY WITH ALL API ENDPOINTS
Test INPUT and REFERENCE CSV files are also included in the repository.

- ACCESS JSP HOME PAGE FOR UI at ``` http://localhost:8080/ ``` 
- ACCESS JSP REPORT PAGE FOR SCHEDULED REPORT at ``` http://localhost:8080/report ```

## Contributors
- Yash Singh

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
