<#import "parts/common.ftl" as c>

<@c.page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="container">
        <p><h1> List of users </h1><td><a href="/main"> Go to home page</a></td></p>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>>Role</th>
            </tr>
            </thead>
    <#list users as user>
            <tbody>
            <tr>
                <th>${user.username}</th>
                <th><#list user.roles as role>${role}<#sep>, </#list></th>
                <th><a href="/user/${user.id}">edit</a></th>
            </tr>
            </tbody>
    </#list>
        </table>
    </div>

    </body>
    </html>
</@c.page>