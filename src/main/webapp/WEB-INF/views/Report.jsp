<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generated Report</title>
</head>
<body>

<h2>Generated Report</h2>

<table border="1">
    <thead>
    <tr>
        <th>Outfield1</th>
        <th>Outfield2</th>
        <th>Outfield3</th>
        <th>Outfield4</th>
        <th>Outfield5</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="report" items="${reportDataList}">
        <tr>
            <td>${report.outfield1}</td>
            <td>${report.outfield2}</td>
            <td>${report.outfield3}</td>
            <td>${report.outfield4}</td>
            <td>${report.outfield5}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
