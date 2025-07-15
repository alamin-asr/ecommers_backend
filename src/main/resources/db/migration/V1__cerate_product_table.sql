CREATE TABLE product (
                         id                 SERIAL PRIMARY KEY,
                         name               VARCHAR(255)      NOT NULL,
                         description        TEXT,
                         brand              VARCHAR(255),
                         price              NUMERIC(19, 2),
                         category           VARCHAR(255),
                         release_date       TIMESTAMPTZ,                 -- matches JsonFormat with timezone
                         product_available  BOOLEAN           NOT NULL DEFAULT FALSE,
                         stock_quantity     INTEGER,
                         image_name         VARCHAR(255),
                         image_type         VARCHAR(255),
                         image_data         BYTEA                             -- byte[] + @Lob
);