insert into feed (id, user_id, unread, time, source_id, type_id, rank, data)
values
    (0, 0,  TRUE, CURRENT_TIME, 0, 0, 0, 'test query'),
    (1, 1,  TRUE, CURRENT_TIME, 1, 0, 0, 'test query2');

insert into text_patterns (type_id, type, pattern)
values
    (0, 0,  TRUE, CURRENT_TIME, 0, 0, 0, 'test query'),
    (1, 1,  TRUE, CURRENT_TIME, 1, 0, 0, 'test query2');


