<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<u:html title="Список пользователей">
    <h2>Список пользователей</h2>
    <table>
        <tr>
            <th>Логин</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Роль</th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td class="content">${user.login}</td>
                <td class="content">${user.lastName}</td>
                <td class="content">${user.firstName}</td>
                <td class="content">${user.middleName}</td>
                <td class="content">${user.role.name}</td>
                <td class="empty">
                    <c:url var="urlUserEdit" value="/user/edit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <a href="${urlUserEdit}" class="edit"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlUserEdit" value="/user/edit.html"/>
    <a href="${urlUserEdit}" class="add-button">Добавить</a>
</u:html>