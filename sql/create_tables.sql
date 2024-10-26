-- users table
CREATE TABLE users
(
    user_id    SERIAL PRIMARY KEY,
    user_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- files table
CREATE TABLE files
(
    file_id    SERIAL PRIMARY KEY,
    file_name  VARCHAR(100) NOT NULL,
    file_type  VARCHAR(255) NOT NULL,
    file_size  BIGINT NOT NULL,
    file_url   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);