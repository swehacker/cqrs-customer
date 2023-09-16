CREATE TABLE eventstore
(
    id           BIGSERIAL PRIMARY KEY,
    version      INTEGER   NOT NULL,
    aggregate_id uuid      NOT NULL DEFAULT gen_random_uuid(),
    event_type   TEXT      NOT NULL,
    event_data   jsonb     NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE customer
(
    id                 uuid PRIMARY KEY   DEFAULT gen_random_uuid(),
    first_name         TEXT      NOT NULL,
    last_name          TEXT      NOT NULL,
    preferred_name     TEXT      NOT NULL,
    preferred_language TEXT      NOT NULL,
    email              TEXT      NOT NULL,
    mobile             TEXT      NOT NULL,
    civic_number       TEXT      NOT NULL,
    birth_date         date      NOT NULL,
    addresses          jsonb,
    consents           jsonb,
    created_at         TIMESTAMP NOT NULL DEFAULT now()
);