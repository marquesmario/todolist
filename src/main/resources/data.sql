INSERT INTO todo(id, title, description, done) VALUES(1, 'Title1', 'Description1', false);
INSERT INTO todo(id, title, description, done) VALUES(2, 'Title2', 'Description2', true);
INSERT INTO todo(id, title, description, done) VALUES(3, 'Title3', 'Description3', false);
INSERT INTO todo(id, title, description, done) VALUES(4, 'Title4', 'Description4', false);
INSERT INTO todo(id, title, description, done) VALUES(5, 'Title5', 'Description5', false);

INSERT INTO task(id, title, description, done, todo_id) VALUES(6, 'Title6', 'Description6', false, 1);
INSERT INTO task(id, title, description, done, todo_id) VALUES(7, 'Title7', 'Description7', true, 2);
INSERT INTO task(id, title, description, done, todo_id) VALUES(8, 'Title8', 'Description8', false, 3);
INSERT INTO task(id, title, description, done, todo_id) VALUES(9, 'Title9', 'Description9', true, 4);
INSERT INTO task(id, title, description, done, todo_id) VALUES(10, 'Title10', 'Description10', false, 5);
INSERT INTO task(id, title, description, done, todo_id) VALUES(11, 'Title11', 'Description11', false, 3);
INSERT INTO task(id, title, description, done, todo_id) VALUES(12, 'Title12', 'Description12', false, 4);

INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(13, 'Title13', 'Description13', '2019-03-22', null, false, 6);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(14, 'Title14', 'Description14', '2019-03-21', '2019-03-25', true, 7);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(15, 'Title15', 'Description15', '2019-03-20', '2019-03-27', true, 7);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(16, 'Title16', 'Description16', '2019-05-22', null, false, 8);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(17, 'Title17', 'Description17', '2019-04-22', '2019-05-01', true, 9);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(18, 'Title18', 'Description18', '2019-06-02', null, false, 10);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(19, 'Title19', 'Description19', '2019-02-21', null, false, 11);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(20, 'Title20', 'Description20', '2019-07-11', null, false, 12);
INSERT INTO item(id, title, description, start_date, end_date, done, task_id) VALUES(21, 'Title21', 'Description21', '2019-08-20', null, false, 11);