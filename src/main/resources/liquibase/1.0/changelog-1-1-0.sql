ALTER TABLE companies
ADD COLUMN image_id INT,
ADD CONSTRAINT fk_image
FOREIGN KEY (image_id) REFERENCES images(id);

ALTER TABLE phones
ADD COLUMN company_id INT,
ADD CONSTRAINT fk_company
FOREIGN KEY (company_id) REFERENCES companies(id);

ALTER TABLE events
ADD COLUMN company_id INT,
ADD CONSTRAINT fk_company
FOREIGN KEY (company_id) REFERENCES companies(id);

ALTER TABLE users
ADD COLUMN country_id INT,
ADD CONSTRAINT fk_country
FOREIGN KEY (country_id) REFERENCES countries(id);

ALTER TABLE tickets
ADD COLUMN event_id INT,
ADD CONSTRAINT fk_event
FOREIGN KEY (event_id) REFERENCES events(id);

ALTER TABLE images
ADD COLUMN event_id INT,
ADD CONSTRAINT fk_event
FOREIGN KEY (event_id) REFERENCES events(id);

CREATE TABLE events_performers (
    event_id INT,
    performer_id INT,
    PRIMARY KEY (event_id, performer_id),
    CONSTRAINT fk_event
    FOREIGN KEY (event_id) REFERENCES events(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_performer
    FOREIGN KEY (performer_id) REFERENCES performers(id)
    ON DELETE CASCADE
);

ALTER TABLE users
ADD COLUMN wallet_id INT,
ADD CONSTRAINT fk_wallet
FOREIGN KEY (wallet_id) REFERENCES wallets(id);

ALTER TABLE phones
ADD COLUMN user_id INT,
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE events
ADD COLUMN place_id INT,
ADD CONSTRAINT fk_place
FOREIGN KEY (place_id) REFERENCES places(id);

ALTER TABLE payment_histories
ADD COLUMN event_id INT,
ADD CONSTRAINT fk_event
FOREIGN KEY (event_id) REFERENCES events(id);

ALTER TABLE payment_histories
ADD COLUMN ticket_id INT,
ADD CONSTRAINT fk_ticket
FOREIGN KEY (ticket_id) REFERENCES tickets(id);

ALTER TABLE payment_histories
ADD COLUMN user_id INT,
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES users(id);