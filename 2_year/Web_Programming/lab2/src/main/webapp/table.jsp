<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.10.2023
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<p>Cooridnate X: ${applicationScope['x']}</p>--%>
<%--<tr>--%>
<%--<td>${applicationScope['x']}</td>--%>
<%--<td>${applicationScope['y']}</td>--%>
<%--<td>${applicationScope['r']}</td>--%>
<%--<td>${applicationScope['workTime']}</td>--%>
<%--<td>${applicationScope['date']}</td>--%>
<%--<td>${applicationScope['hit']}</td>--%>
<%--</tr>--%>
<body>
<%
    List<Object[]> data = (List<Object[]>) application.getAttribute("tableData");

    if (data != null) {
        for (Object[] row : data) {
%>
<tr>
    <td><%= row[0]%></td>
    <td><%= row[1]%></td>
    <td><%= row[2]%></td>
    <td><%= row[3]%></td>
     <td><%= row[4]%></td>
    <td><%= row[5]%></td>
</tr>
<%
        }
    }
%>
</body>