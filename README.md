# To Do List API (Spring, Docker Compose and Mysql)


The To Do List API is the tasks api, created with Spring Boot, Docker Compose and Mysql.


## Entities

- AuthUser (id, username, password)
- Task (id, name, description, finish)


## Use Cases

### AuthUser
- login
- register
- findByUsername

### Task
- createTask (Create task)
- updateTask (Update task by task id)
- findTaskById (Find task by id)
- findAllTasks (Find all created tasks)
- deleteTask (Delete task by id)
- findFinishTasks (Find finish tasks)
- findNotFinishTasks (Find not finish tasks)
