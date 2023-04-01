insert into feed (id, user_id, unread, time, source_id, type_id, rank, data)
values
    (1000, 0,  true, current_date, 1000, 0, 0, 'test query'),
    (1001, 1,  true, current_date, 1001, 1, 0, 'test query2');

insert into text_patterns (type_id, type, pattern)
values
    (1000, 'first text for test', 'another text for test 0'),
    (1001, 'second text for test', 'another text for test 1');


