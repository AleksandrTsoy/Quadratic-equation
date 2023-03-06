CREATE TABLE IF NOT EXISTS equation_data
(
    id          BIGSERIAL PRIMARY KEY,
    a           DECIMAL(19, 4),
    b           DECIMAL(19, 4),
    c           DECIMAL(19, 4),
    x1          DECIMAL(19, 4),
    x2          DECIMAL(19, 4)
);