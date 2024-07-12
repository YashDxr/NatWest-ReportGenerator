<!DOCTYPE html>
<html>
<head>
    <title>Report Generator</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        form input[type="file"] {
            display: block;
            margin-bottom: 10px;
        }
        form button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        form button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Welcome to Report Generator</h1>
<form action="/api/files/upload" method="post" enctype="multipart/form-data">
    <label for="inputfile">Insert Input File (CSV file):</label>
    <input type="file" id="inputfile" name="inputfile" accept=".csv"><br><br>

    <label for="referencefile">Insert Reference File (CSV file):</label>
    <input type="file" id="referencefile" name="referencefile" accept=".csv"><br><br>

    <button type="submit">Generate Report</button>
</form>

<form action="/api/reports/schedule" method="post">
    <input type="datetime-local" name="datetime"><br><br>
    <button type="submit">Generate Report</button>
</form>
<form action="/api/files/generatereport">
    <button type="submit">Create Report from Existing Input & Reference Table</button>
</form>
<form action="/api/files/getreport">
    <button type="submit">Generate Report from Output Table (Already Generated Reports)</button>
</form>

</body>
</html>
