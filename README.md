# WeathAir-back

root : http://localhost:8080

/users : Access for everyone (GET-POST), ADMINISTRATOR can PUT(update a user, or BAN a user , or UNBAN a user), he also can DELETE him.

root : http://localhost:8080/users

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/users"</td>         <td> list all users (and administrator by the way) </td> </tr>
        <tr> <td>GET</td>         <td>"/users/{id} "</td>     <td> list a user by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/users"</td>         <td> create a user (user or administrator) </td> </tr>
        <tr> <td>PUT</td>         <td>"/users/{id} "</td>         <td> update a user </td> </tr>
        <tr> <td>PUT</td>         <td>"/users/ban?=id "</td>         <td>  ban a user with him id </td> </tr>
        <tr> <td>PUT</td>         <td>"/users/unban?=id "</td>         <td> unban a user with him id </td> </tr>
        <tr> <td>DELETE</td>     <td>"/users/{id}"</td>     <td> delete a user with him id</td> </tr>
    </tbody>
</table>

/airindicators : Access for everyone (GET), ADMINISTRATOR (GET-POST-PUT-DELETE)

root : http://localhost:8080/airindicators

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/airindicators"</td>         <td> list all air indicators </td> </tr>
        <tr> <td>GET</td>         <td>"/airindicators/{id} "</td>     <td> list an airindicator by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/airindicators"</td>         <td> create an airindicators  </td> </tr>
        <tr> <td>PUT</td>         <td>"/airindicators/{id} "</td>         <td> update an airindicator </td> </tr>
        <tr> <td>DELETE</td>     <td>"/airindicators/{id}"</td>     <td> delete an airindicator with him id</td> </tr>
    </tbody>
</table>

/meteoindicators : Access for everyone (GET), USER can (POST) and ADMINISTRATOR can (POST-PUT-DELETE)

root : http://localhost:8080/meteoindicators

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/meteoindicators"</td>         <td> list all meteo indicators </td> </tr>
        <tr> <td>GET</td>         <td>"/meteoindicators/{id} "</td>     <td> list a meteoindicator by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/meteoindicators"</td>         <td> create a meteoindicator  </td> </tr>
        <tr> <td>PUT</td>         <td>"/meteoindicators/{id} "</td>         <td> update a meteoindicator </td> </tr>
        <tr> <td>DELETE</td>     <td>"/meteoindicators/{id}"</td>     <td> delete a meteoindicator with him id</td> </tr>
    </tbody>
</table>

/favorites : Just for USER and ADMINISTRATOR, USER Can (GET) and ADMINISTRATOR can (GET-POST-PUT-DELETE)

root : http://localhost:8080/favorites

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/favorites"</td>         <td> list all favorites indicators </td> </tr>
        <tr> <td>GET</td>         <td>"/favorites/{id} "</td>     <td> list a favorite by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/favorites"</td>         <td> create a favorite  </td> </tr>
        <tr> <td>PUT</td>         <td>"/favorites/{id} "</td>         <td> update a favorite </td> </tr>
        <tr> <td>DELETE</td>     <td>"/favorites/{id}"</td>     <td> delete a favorite with him id</td> </tr>
    </tbody>
</table>

/notifications : Just for USER and ADMINISTRATOR, USER can (GET) and ADMINISTRATOR can (GET-POST-PUT-DELETE)

root : http://localhost:8080/notifications

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/notifications"</td>         <td> list all notifications  </td> </tr>
        <tr> <td>GET</td>         <td>"/notifications/{id} "</td>     <td> list a notification by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/notifications"</td>         <td> create a notification  </td> </tr>
        <tr> <td>PUT</td>         <td>"/notifications/{id} "</td>         <td> update a notification </td> </tr>
        <tr> <td>DELETE</td>     <td>"/notifications/{id}"</td>     <td> delete a notification with him id</td> </tr>
    </tbody>
</table>

FORUM PART : 

/topics : ACCESS FOR EVERYONE (GET), ADMINISTRATOR can (GET-POST-PUT-DELETE)

root : http://localhost:8080/topics

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/topics"</td>         <td> list all topics  </td> </tr>
        <tr> <td>GET</td>         <td>"/topics/{id} "</td>     <td> list a topic by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/topics"</td>         <td> create a topic  </td> </tr>
        <tr> <td>PUT</td>         <td>"/topics/{id} "</td>         <td> update a topic </td> </tr>
        <tr> <td>DELETE</td>     <td>"/topics/{id}"</td>     <td> delete a topic with him id</td> </tr>
    </tbody>
</table>

/posts : ACCESS FOR EVERYONE (GET), USER can (GET-POST), ADMINISTRATOR can (GET-POST-PUT-DELETE)

root : http://localhost:8080/posts

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/posts"</td>         <td> list all posts  </td> </tr>
        <tr> <td>GET</td>         <td>"/posts/{id} "</td>     <td> list a post by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/posts"</td>         <td> create a post  </td> </tr>
        <tr> <td>PUT</td>         <td>"/posts/{id} "</td>         <td> update a post </td> </tr>
        <tr> <td>DELETE</td>     <td>"/posts/{id}"</td>     <td> delete a post with him id</td> </tr>
    </tbody>
</table>

/messages : ACCESS FOR EVERYONE (GET), USER can (GET-POST), ADMINISTRATOR can (GET-POST-PUT-DELETE)

root : http://localhost:8080/messages 

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/messages"</td>         <td> list all messages  </td> </tr>
        <tr> <td>GET</td>         <td>"/messages/{id} "</td>     <td> list a message by him Id </td> </tr>
        <tr> <td>POST</td>         <td>"/messages"</td>         <td> create a message </td> </tr>
        <tr> <td>PUT</td>         <td>"/messages/{id} "</td>         <td> update a message </td> </tr>
        <tr> <td>DELETE</td>     <td>"/messages/{id}"</td>     <td> delete a message with him id</td> </tr>
    </tbody>
</table>

TOWNSHIPS : ACCESS FOR ADMINISTRATOR ONLY (GET-POST-PUT-DELETE)

root : http://localhost:8080/townships 

<table>
    <thead>
        <tr> <th> Verb </th> <th> root </th> <th> description </th> </tr>
    </thead>
    <tbody>
        <tr> <td>GET</td>         <td>"/townships"</td>         <td> list all  townships </td> </tr>
        <tr> <td>GET</td>         <td>"/townships/insee/{inseeCode} "</td>     <td> list a township by him inseeCode </td> </tr>
        <tr> <td>GET</td>         <td>"/townships/name/{name} "</td>     <td> list a township by him townName </td> </tr>
        <tr> <td>POST</td>         <td>"/townships/{inseeCode}"</td>         <td> create a township  </td> </tr>
        <tr> <td>PUT</td>         <td>"/townships/name/{inseeCode} "</td>         <td> update a township name by using insee code </td> </tr>
        <tr> <td>PUT</td>         <td>"/townships/population/{inseeCode} "</td>         <td> update the population by using insee code </td> </tr>
        <tr> <td>DELETE</td>     <td>"/townships/{inseeCode}"</td>     <td> delete a township with him inseeCode</td> </tr>
    </tbody>
</table>

LOGIN : ACCESS FOR EVERYONE, CREATE A USER 

root : http://localhost:8080/login

First step create a user : Pseudo/Password/email/Township/role
2nd step : generate a BCRYPT Password : http://localhost:8080/passToEncode="id", take the pass and put it in Database.
3rd step : login with your email & password (not encoded pasword), take your Bearer token 
4th step : when you make an action (get post put delete) put your Bearer token instead of authenticate
