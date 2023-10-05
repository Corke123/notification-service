CREATE TABLE notification
(
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    incident_id           UUID NOT NULL,
    related_incidents_ids JSON NOT NULL
);