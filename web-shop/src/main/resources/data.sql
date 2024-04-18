INSERT INTO USERS (user_role, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description)
VALUES (
    'admin',
    'John',
    'Doe',
    'johndoe',
    'password',
    'john.doe@example.com',
    '+1234567890',
    '1990-01-01',
    '/path/to/profile/picture.jpg',
    'I am admin yo'
);

-- Inserting data into the User table
INSERT INTO USERS (user_role, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('seller','John', 'Doe', 'johndoe2', 'password', 'john@example.com', '123456789', '1990-01-01', 'profile.jpg', 'I am a seller', FALSE),
('seller','Alice', 'Smith', 'alicesmith', 'password',  'alice@example.com', '987654321', '1995-05-15', 'avatar.jpg', 'I am also a seller', FALSE);

-- Inserting data into the User table for buyers
INSERT INTO USERS (user_role, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('buyer','Emily', 'Johnson', 'emilyjohnson', 'password', 'emily@example.com', '555123456', '1992-09-20', 'emily.jpg', 'I am a buyer', FALSE),
('buyer','Michael', 'Williams', 'michaelwilliams', 'password', 'michael@example.com', '555987654', '1985-07-12', 'michael.jpg', 'I am also a buyer', FALSE);
INSERT INTO USERS (user_role, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('seller','Matthew', 'Matthewson', 'matthewmatthewson', 'pword', 'matthewsonmatthew@example.com', '555423456', '1993-09-20', 'mat.jpg', 'I am a seller', FALSE);
-- Inserting data into the Review table
INSERT INTO REVIEWS (score, comment, review_date, buyer_id, seller_id, poster)
VALUES (5, 'Great seller, highly recommended!', '2024-04-07', 2, 1, 'BUYER'),
(4, 'Good service, thank you!', '2024-04-06', 1, 2, 'BUYER'),
(3, 'Product was okay, could be better.', '2024-04-05', 1, 1, 'BUYER'),
(5, 'Excellent seller, very satisfied!', '2024-04-04', 2, 2, 'BUYER');
INSERT INTO REPORTS(DATE_POSTED,ID,WHO_IS_REPORTED_ID,WHO_REPORTED_ID,REASON,STATUS) VALUES
('2024-03-03','1','1','2','Scammer, I sent money, but didnt receive item.','SUBMITTED'),
('2024-04-03','2','2','3','He said that I will receive 5 books but I only received ONE...','ACCEPTED'),
('2024-05-03','3','3','4','She sold me a TV that, TV got to my house but I dont like it and i want my money back.','REJECTED');
INSERT INTO CATEGORIES (category_name)
VALUES ('patike'),
       ('naocare'),
       ('carape'),
       ('monitori'),
       ('procesori');
INSERT INTO PRODUCTS(BUYER_REVIEW,PRICE,SELLER_REVIEW,SOLD,BUYER_ID,ID,SALE_START_DATE,SELLER_ID,DESCRIPTION,CATEGORY,IMAGE_PATH,NAME,TYPE_OF_SALE) VALUES
(TRUE,'10000',FALSE,FALSE,'1','1','2024-03-02','2','AMD RYZEN 5 3600X - nov, nekoriscen, odlicno stanje..','procesori','ryzen.jpg','AMD RYZEN 5 3600X', 'FIXED_PRICE'),
(FALSE,'15000',TRUE,TRUE,'2','2','2024-06-21','1','DELL MONITOR - polovan, ocuvan, odlicno stanje..','monitori','monitor.jpg','DELL Monitor', 'AUCTION'),
(TRUE,'20000',FALSE,FALSE,'3','3','2024-02-08','6','NIKE AIRMAX 90 - nenosene patike, nove','patike','nike.jpg','NIKE AIRMAX 90', 'FIXED_PRICE');
INSERT INTO OFFERS(OFFER_AMOUNT_MONEY,BUYER_ID,ID) VALUES ('10000','1','1'),('20000','3','2'),('30000','2','3');
INSERT INTO PRODUCTS_OFFERS(OFFERS_ID, PRODUCT_ID) VALUES ('1','3'),('2','2'),('3','1');
