<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="request.view.title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="request.view.table.description"/></th>
            <td class="content">${request.description}</td>
        </tr>
        <tr>
            <th><fmt:message key="request.view.table.status"/></th>
            <td class="content"><fmt:message key="${request.status.name}"/></td>
        </tr>
    </table>
    <c:url var="urlRequestEdit" value="/request/edit.html">
        <c:param name="id" value="${request.id}"/>
    </c:url>
    <a href="${urlRequestEdit}" class="edit-button"><fmt:message key="request.view.button.edit"/></a>
</u:html>