CREATE INDEX idx_accounts_user_ID ON accounts (user_ID);

CREATE INDEX idx_logs_user_ID ON accounts (user_ID);

CREATE INDEX idx_masterRecord_sender_ID ON masterRecord (sender_ID);

CREATE INDEX idx_masterRecord_receiver_ID ON masterRecord (receiver_ID);