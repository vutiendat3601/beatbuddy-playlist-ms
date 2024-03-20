CREATE TABLE IF NOT EXISTS playlists (
	playlist_id uuid NOT NULL PRIMARY KEY,
	id bpchar(16) UNIQUE NOT NULL,
	"name" varchar(255) NOT NULL,
	thumbnail varchar(255) NULL,
	description text NULL,
	is_public bool NOT NULL DEFAULT false,
	duration_sec int NOT NULL DEFAULT 0,
	tracks_json text NOT NULL DEFAULT '[]',
	created_by varchar(255) NULL,
	updated_by varchar(255) NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL
);
