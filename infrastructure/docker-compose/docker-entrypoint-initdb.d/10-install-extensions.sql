CREATE EXTENSION IF NOT EXISTS pg_stat_statements;
COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";