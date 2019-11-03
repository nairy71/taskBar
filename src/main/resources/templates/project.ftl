<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as l>

<@c.page>
<@l.logout/>

<form method="post" action="/addProject">

    <h4>Добавить проект</h4>
    <label for="summary">Имя</label>
    <input id="summary" name="name" type="text" value="">
    <br />
    <label for="summary">Описание</label>
    <textarea id="description" name="description"></textarea>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <p><button type="submit">Добавить</button></p>

</form>
<div>Список проектов</div>
<ul>
<#list projects as project>
<li>
    <div>
        <b>${project.name}</b>

        <form action="/editProject" method="get" style="display:inline;">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${project.id}"/>
            <button type="submit">Редактировать</button>
        </form>

        <form action="/deleteProject" method="post" style="display:inline;">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${project.id}"/>
            <button type="submit">Удалить</button>
        </form>

    </div>
</li>
<#else>
Пока нет проектов.

</#list>
</ul>










</@c.page>