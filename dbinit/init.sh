set -e
set -u

function create_database() {
    local database="$1"
    echo "Creating database '$database'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
        CREATE DATABASE $database;
EOSQL
}

if [ -n "$POSTGRES_DB" ]; then
    create_database "$POSTGRES_DB"
    echo "Database '$POSTGRES_DB' created"
fi
