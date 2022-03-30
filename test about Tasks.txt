create database tasks;
use tasks;

create table performers
(
    id         int not null auto_increment unique primary key,
    surname    char(25),
    name       char(25),
    s_name     char(25),
    manager_id int
);

create table manager
(
    id         int primary key,
    task_id    int,
    project_id int
);

create table tasks
(
    task_id    int primary key,
    nameOfTask char(20),
    time       char(10),
    price      int(10)
);

create table projects
(
    project_id    int primary key,
    nameOfProject char(25),
    difficulty    char(15),
    time          char(15)
);

insert into performers (surname, name, s_name, manager_id)
values ('Петров', 'Петя', 'Сергеевич', 1),
       ('Смиронов', 'Сергей', 'Сергеевич', 2),
       ('Федоровв', 'Сергей', 'Юрьевич', 3),
       ('Смиронов', 'Сергей', 'Сергеевич', 4);

insert into manager (id, task_id, project_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 2),
       (5, 5, 2);

insert into projects (project_id, nameOfProject, difficulty, time)
VALUES (1, 'Проект 1', 'Сложный', '2 дня'),
       (2, 'Проект 2', 'Легкий', '1 день');

insert into tasks (task_id, nameOfTask, time, price)
VALUES (1, 'Задача 1', '4 часа', 2000),
       (2, 'Задача 2', '8 часов', 2500),
       (3, 'Задача 3', '4 часа', 1000),
       (4, 'Задача 4', '5 часов', 3000),
       (5, 'Задача 5', '3 часа', 500);

alter table performers
    add constraint fk_performance_manager foreign key (manager_id)
        references manager (id);

alter table manager
    add constraint fk_task_manager foreign key (task_id)
        references tasks (task_id);

alter table manager
    add constraint fk_manager_project foreign key (project_id)
        references projects (project_id);
/*
select tasks.tasks.nameOfTask
from tasks,
     manager,
     projects
where tasks.task_id = manager.task_id
  and projects.project_id = manager.project_id
  and projects.difficulty = 'Сложный';
*/

select tasks.tasks.nameOfTask, projects.difficulty
from tasks
         inner join manager on tasks.task_id = manager.task_id
         inner join projects on manager.project_id = tasks.projects.project_id
where tasks.projects.difficulty = 'Сложный';

select sum(tasks.price), projects.nameOfProject
from tasks
         inner join manager on tasks.task_id = manager.task_id
         inner join projects on manager.project_id = tasks.projects.project_id
group by projects.nameOfProject;

select sum(tasks.price) as sum, projects.nameOfProject
from tasks
         inner join manager on tasks.task_id = manager.task_id
         inner join projects on manager.project_id = tasks.projects.project_id
group by projects.nameOfProject
having sum > 4000;

update projects
set nameOfProject = 'Проект 25'
where nameOfProject = 'Проект 1';

select *
from projects;
