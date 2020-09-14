<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BLOG</title>
</head>
<body>
<h1>Blog</h1>
<div>
    <fieldset>
        <legend>Add note</legend>
        <form name="blogPost" action="save" method="POST">
            <@ui.formInput id="t1" name="title" label="Title"/> <br/>
            <@ui.formInput id="t2" name="body" label="Text"/> <br/>
            <input type="submit" value="Save" />
        </form>
    </fieldset>
</div>

<div>
    <fieldset>
        <legend>Search</legend>
        <form name="searchForm" action="search" method="POST">
            <@ui.formInput id="t3" name="searchPost" label="Search"/> <br/>
            <input type="submit" value="Search" />
        </form>
    </fieldset>
</div>

<p><span style="color: red; "> <#if message??>${message}<#else></#if></span></p>

<ul>
    <#list blog as item>
        <li>${item.title}<br>${item.body}</li>
        <form name="searchForm" action="delete?index=${item.id}" method="POST">
            <input type="submit" name="Delete" value="Delete"/>
        </form>
    </#list>
</ul>
</body>
</html>