<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="request.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="request.list.table.id"/></th>
            <th><fmt:message key="request.list.table.description"/></th>
            <th><fmt:message key="request.list.table.driver"/></th>
            <th><fmt:message key="request.list.table.status"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td class="content">${request.id}</td>
                <td class="content">${request.description}</td>
                <td class="content">${request.driverId}</td>
                <td class="content"><fmt:message key="${request.status.name}"/></td>
                <td class="empty">
                    <c:url var="urlRequestView" value="/request/view.html">
                        <c:param name="id" value="${request.id}"/>
                    </c:url>
                    <a href="${urlRequestView}" class="view"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlRequestEdit" value="/request/edit.html"/>
    <a href="${urlRequestEdit}" class="add-button"><fmt:message key="request.list.button.add"/></a>
</u:html>