<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="car.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="car.list.table.id"/></th>
            <th><fmt:message key="car.list.table.model"/></th>
            <th><fmt:message key="car.list.table.places"/></th>
            <th><fmt:message key="car.list.table.carrying"/></th>
            <th><fmt:message key="car.list.table.condition"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td class="content">${car.id}</td>
                <td class="content"><fmt:message key="${car.model.name}"/></td>
                <td class="content">${car.places}</td>
                <td class="content">${car.carrying}</td>
                <td class="content"><fmt:message key="${car.condition.name}"/></td>
                <td class="empty">
                    <c:url var="urlCarEdit" value="/car/edit.html">
                        <c:param name="id" value="${car.id}"/>
                    </c:url>
                    <a href="${urlCarEdit}" class="edit"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentUser.role == 'DRIVER'}">
        <c:url var="urlCarEdit" value="/car/edit.html"/>
        <a href="${urlCarEdit}" class="add-button"><fmt:message key="car.list.button.add"/></a>
    </c:if>
</u:html>