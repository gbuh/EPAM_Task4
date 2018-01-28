<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty car}"><jsp:useBean id="car" class="domain.Car"/></c:if>
<c:choose>
    <c:when test="${not empty car.id}">
        <fmt:message var="title" key="car.edit.title.edit"/>
        <c:url var="urlBack" value="/car/list.html"/>
    </c:when>
    <c:otherwise><fmt:message var="title" key="car.edit.title.add"/>
        <c:url var="urlBack" value="/car/list.html"/>
    </c:otherwise>

</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlCarSave" value="/car/save.html"/>
    <c:url var="urlCarDelete" value="/car/delete.html"/>
    <form action="${urlCarSave}" method="post">
        <c:if test="${not empty car.id}">
            <label for="id"><fmt:message key="car.edit.form.id"/>:</label>
            <input id="id" name="id" value="${car.id}" readonly>
        </c:if>
        <label for="model"><fmt:message key="car.edit.form.model"/>:</label>
        <select id="model" name="model">
            <c:forEach var="model" items="${models}">
                <c:choose>
                    <c:when test="${model.id == car.model.id}"><c:set var="selected" value="selected"/></c:when>
                    <c:otherwise><c:remove var="selected"/></c:otherwise>
                </c:choose>
                <option value="${model.id}" ${selected}><fmt:message key="${model.name}"/></option>
            </c:forEach>
        </select>
        <label for="places"><fmt:message key="car.edit.form.places"/>:</label>
        <input id="places" name="places" value="${car.places}">
        <label for="carrying"><fmt:message key="car.edit.form.carrying"/>:</label>
        <input id="carrying" name="carrying" value="${car.carrying}">
        <label for="condition"><fmt:message key="car.edit.form.condition"/>:</label>
        <select id="condition" name="condition">
            <c:forEach var="condition" items="${conditions}">
                <c:choose>
                    <c:when test="${condition.id == car.condition.id}"><c:set var="selected" value="selected"/></c:when>
                    <c:otherwise><c:remove var="selected"/></c:otherwise>
                </c:choose>
                <option value="${condition.id}" ${selected}><fmt:message key="${condition.name}"/></option>
            </c:forEach>
        </select>
        <button class="save"><fmt:message key="car.edit.button.save"/></button>
        <c:if test="${not empty car.id}">
            <c:set var="disabled" value="disabled"/>
            <button class="delete" formaction="${urlCarDelete}" formmethod="post" ${disabled}><fmt:message key="car.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="car.edit.button.reset"/></button>
        <button class="back" formaction="${urlBack}" formmethod="get"><fmt:message key="car.edit.button.cancel"/></button>
    </form>
</u:html>