CREATE TABLE IF NOT EXISTS books
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publishing_year VARCHAR(4),
    genre VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS outbox
(
    id SERIAL PRIMARY KEY,
    event VARCHAR(50) NOT NULL,
    event_id INT NOT NULL,
    payload TEXT,
    created_at TIMESTAMP
);
