<#import "parts/common.ftl" as c>

<@c.page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Bootstrap 4 Website Example</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <style>
            .fakeimg {
                height: 200px;
                background: #aaa;
            }
        </style>
    </head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>User editor</h1>

    <form class="form-inline d-block ml-auto" action="/user" method="post">
        <input class="form-control text-center" type="text" name="username" value="${user.username}"/>
        <#list roles as role>
            <div>
                <label><input class="form-control" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <button class="btn btn-outline-success" type="submit">Save</button>
    </form>
</div>
</body>
</@c.page>