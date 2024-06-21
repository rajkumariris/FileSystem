CREATE TABLE file
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    deleted_at     datetime NULL,
    name           VARCHAR(255) NULL,
    path_directory VARCHAR(255) NULL,
    CONSTRAINT pk_file PRIMARY KEY (id)
);

CREATE TABLE session
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    token      VARCHAR(255) NULL,
    user_id    BIGINT NULL,
    is_active  BIT(1) NOT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);