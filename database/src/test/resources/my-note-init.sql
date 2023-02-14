delete
from MY_NOTE
where 1 = 1;

delete
from PERSON
where 1 = 1;

delete
FROM MY_NOTE_CATEGORY
where 1 = 1;

insert into PERSON
values (987654321, '', 'alializadeh@gmail.com', 'علی', 'علی زاده', '09118445768');

insert into MY_NOTE_CATEGORY
values(987654321,'Category 1','DONE','PROJECT_1');


insert into MY_NOTE (id, creation_date, description, is_active, priority, title, my_note_category_id, person_id)
values (87384, DATE '2009-12-15', 'Project sub task', true, 5, 'SubTask1', 987654321, 987654321);

