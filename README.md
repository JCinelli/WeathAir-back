# WeathAir-back

root : http://localost:8080

/users

Verb | root | description
--- | --- | ---|
GET | /users | list all users (and administrator by the way)
--- | --- | ---|
GET | /users/{id} | list a user by him Id
--- | --- | ---|
POST | /users | create a user (user or administrator)
--- | --- | ---|
PUT | /users/{id} | update a user
--- | --- | ---|
PUT | /users/ban?=id | ban a user with him id
--- | --- | ---|
PUT | /users/unban?=id | unban a user with him id
--- | --- | ---|
DELETE | users/{id} | delete a user with him id
--- | --- | ---|
