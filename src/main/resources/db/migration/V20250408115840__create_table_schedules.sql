CREATE TABLE SCHEDULES (
    id BIGSERIAL not null primary key,
    start_in timestamp not null,
    end_in timestamp not null,
    client_id BIGSERIAL not null,
    CONSTRAINT UK_SCHEDULE_INTERVAL UNIQUE (start_in, end_in),
    CONSTRAINT FK_CLIENTS_SCHEDULES FOREIGN KEY(client_id) REFERENCES CLIENTS(id)
);