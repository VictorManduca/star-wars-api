CREATE TABLE IF NOT EXISTS planet (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    weather VARCHAR(100),
    terrain VARCHAR(100),
    appeared_in_films INT
);
