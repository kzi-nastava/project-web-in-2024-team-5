INSERT INTO USERS (user_role, name, surname, username, email, phone_number, date_of_birth, profile_picture, description)
VALUES (
    'admin',
    'John',
    'Doe',
    'johndoe',
    'john.doe@example.com',
    '+1234567890',
    '1990-01-01',
    '/path/to/profile/picture.jpg',
    'I am admin yo'
);

-- Inserting data into the User table
INSERT INTO USERS (user_role, name, surname, username, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('seller','John', 'Doe', 'johndoe', 'john@example.com', '123456789', '1990-01-01', 'profile.jpg', 'I am a seller', FALSE),
('seller','Alice', 'Smith', 'alicesmith', 'alice@example.com', '987654321', '1995-05-15', 'avatar.jpg', 'I am also a seller', FALSE);

-- Inserting data into the User table for buyers
INSERT INTO USERS (user_role, name, surname, username, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('buyer','Emily', 'Johnson', 'emilyjohnson', 'emily@example.com', '555123456', '1992-09-20', 'emily.jpg', 'I am a buyer', FALSE),
('buyer','Michael', 'Williams', 'michaelwilliams', 'michael@example.com', '555987654', '1985-07-12', 'michael.jpg', 'I am also a buyer', FALSE);

-- Inserting data into the Review table
INSERT INTO REVIEWS (score, comment, review_date, buyer_id, seller_id, poster)
VALUES (5, 'Great seller, highly recommended!', '2024-04-07', 2, 1, 'BUYER'),
(4, 'Good service, thank you!', '2024-04-06', 1, 2, 'BUYER'),
(3, 'Product was okay, could be better.', '2024-04-05', 1, 1, 'BUYER'),
(5, 'Excellent seller, very satisfied!', '2024-04-04', 2, 2, 'BUYER');

INSERT INTO CATEGORIES (category_name)
VALUES ('patike'),
       ('naocare'),
       ('carape'),
       ('monitori')
