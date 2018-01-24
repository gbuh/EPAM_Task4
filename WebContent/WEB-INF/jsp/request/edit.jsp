<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty request}"><jsp:useBean id="Request" class="domain.Request"/></c:if>
<c:choose>
    <c:when test="${not empty request.id}">
        <fmt:message var="title" key="request.edit.title.edit"/>
        <c:url var="urlBack" value="/request/view.html"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="request.edit.title.add"/>
        <c:url var="urlBack" value="/request/list.html"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlRequestSave" value="/request/save.html"/>
    <c:url var="urlRequestDelete" value="/request/delete.html"/>
    <form action="${urlRequestSave}" method="post">
        <c:if test="${not empty request.id}">
            <label for="id"><fmt:message key="request.edit.form.id"/>:</label>
            <input id="id" name="id" value="${request.id}" readonly>
        </c:if>
        <label for="description"><fmt:message key="request.edit.form.description"/>:</label>
        <input id="description" name="description" value="${request.description}">
        <label for="driverId"><fmt:message key="request.edit.form.driver"/>:</label>
        <input id="driverId" name="driverId" value="${request.driverId}">
        <label for="status"><fmt:message key="request.edit.form.status"/>:</label>
        <select id="status" name="status">
            <c:forEach var="status" items="${statuses}">
                <c:choose>
                    <c:when test="${status.id == request.status.id}"><c:set var="selected" value="selected"/></c:when>
                    <c:otherwise><c:remove var="selected"/></c:otherwise>
                </c:choose>
                <option value="${status.id}" ${selected}><fmt:message key="${status.name}"/></option>
            </c:forEach>
        </select>
        
<!--        <c:if test="${not empty request.id}">
            <label for="status"><fmt:message key="request.edit.form.status"/>:</label>
            <input id="status" value="<fmt:message key="${request.status.name}"/>" readonly>
        </c:if> -->
        
        <button class="save"><fmt:message key="request.edit.button.save"/></button>
        <c:if test="${not empty request.id}">
            <c:if test="${not empty request.description}"><c:set var="disabled" value="disabled"/></c:if>
            <button class="delete" formaction="${urlRequestDelete}" formmethod="post" ${disabled}><fmt:message key="request.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="request.edit.button.reset"/></button>
        <button class="back" formaction="${urlBack}" formmethod="get"><fmt:message key="request.edit.button.cancel"/></button>
    </form>
</u:html>