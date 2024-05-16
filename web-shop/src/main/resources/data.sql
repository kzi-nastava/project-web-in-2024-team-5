INSERT INTO USERS (user_role, average_rating, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description)
VALUES (
    'admin',
    0.0,
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
INSERT INTO USERS (user_role, average_rating, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('seller',0.0,'John', 'Doe', 'johndoe2', 'password', 'john@example.com', '123456789', '1990-01-01', 'profile.jpg', 'I am a seller', FALSE),
('seller',0.0, 'Alice', 'Smith', 'alicesmith', 'password',  'alice@example.com', '987654321', '1995-05-15', 'avatar.jpg', 'I am also a seller', FALSE);

-- Inserting data into the User table for buyers
INSERT INTO USERS (user_role, average_rating, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES
('buyer',0.0,'Emily', 'Johnson', 'emilyjohnson', 'password', 'emily@example.com', '555123456', '1992-09-20', 'emily.jpg', 'I am a buyer', FALSE),
('buyer',0.0, 'Michael', 'Williams', 'michaelwilliams', 'password', 'michael@example.com', '555987654', '1985-07-12', 'michael.jpg', 'I am also a buyer', FALSE);
INSERT INTO USERS (user_role, average_rating, name, lastname, username, password, email, phone_number, date_of_birth, profile_picture, description,  blocked)
VALUES ('seller',0.0, 'Matthew', 'Matthewson', 'matthewmatthewson', 'pword', 'matthewsonmatthew@example.com', '555423456', '1993-09-20', 'mat.jpg', 'I am a seller', FALSE);

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
       ('Accessories'),
       ('Kitchen'),
       ('Home Decor'),
       ('Furniture'),
       ('Books'),
       ('Stationery'),
       ('Electronics'),
       ('Health & Beauty'),
       ('Home & Kitchen'),
       ('procesori');

INSERT INTO PRODUCTS(BUYER_REVIEW,PRICE,SELLER_REVIEW,SOLD,SALE_START_DATE,DESCRIPTION,CATEGORY,IMAGE_PATH,NAME,TYPE_OF_SALE) VALUES
(TRUE,'10000',  FALSE,FALSE,  '2024-03-02',  'AMD RYZEN 5 3600X - nov, nekoriscen, odlicno stanje..',   'procesori','ryzen.jpg','AMD RYZEN 5 3600X', 'FIXED_PRICE'),
(FALSE,'15000', TRUE, TRUE,   '2024-06-21',  'DELL MONITOR - polovan, ocuvan, odlicno stanje..',        'monitori','monitor.jpg','DELL Monitor', 'AUCTION'),
(TRUE,'20000',  FALSE,FALSE,  '2024-02-08',  'NIKE AIRMAX 90 - nenosene patike, nove',                  'patike','nike.jpg','NIKE AIRMAX 90', 'FIXED_PRICE'),
(TRUE, 29.99,   TRUE, FALSE,  '2024-05-01',  'A stylish black leather wallet.',                         'Accessories', '/images/wallet.jpg', 'Leather Wallet', 'FIXED_PRICE'),
(TRUE, 49.99,   TRUE, FALSE,  '2024-04-25',  'A set of 6 ceramic mugs in assorted colors.',             'Kitchen', '/images/mugs.jpg', 'Ceramic Mug Set', 'AUCTION'),
(TRUE, 99.99,   TRUE, FALSE,  '2024-05-03',  'An elegant silver wristwatch for men.',                   'Accessories', '/images/watch.jpg', 'Mens Silver Watch', 'FIXED_PRICE'),
(TRUE, 39.99,   TRUE, FALSE,  '2024-04-28',  'A pack of 10 colorful gel pens for art and writing.',     'Stationery', '/images/gel_pens.jpg', 'Gel Pen Set', 'FIXED_PRICE'),
(TRUE, 149.99,  TRUE, FALSE,  '2024-05-02',  'A modern wooden coffee table with storage.',              'Furniture', '/images/coffee_table.jpg', 'Wooden Coffee Table', 'AUCTION'),
(TRUE, 79.99,   TRUE, FALSE,  '2024-04-29',  'A cozy knitted throw blanket in gray.',                   'Home Decor', '/images/blanket.jpg', 'Knitted Throw Blanket', 'FIXED_PRICE'),
(TRUE, 19.99,   TRUE, FALSE,  '2024-04-26',  'A set of 4 stainless steel steak knives.',                'Kitchen', '/images/knives.jpg', 'Steak Knife Set', 'AUCTION'),
(TRUE, 69.99,   TRUE, FALSE,  '2024-04-30',  'A sleek black leather laptop bag.',                       'Accessories', '/images/laptop_bag.jpg', 'Leather Laptop Bag', 'FIXED_PRICE'),
(TRUE, 79.99,   TRUE, FALSE,  '2024-05-04',  'A collection of 12 classic novels by famous authors.',    'Books', '/images/novels.jpg', 'Classic Novel Collection', 'FIXED_PRICE'),
(TRUE, 29.99,   TRUE, FALSE,  '2024-04-27',  'A set of 3 decorative glass vases in different sizes.',   'Home Decor', '/images/vases.jpg', 'Glass Vase Set', 'AUCTION'),
(TRUE, 39.99,   TRUE, FALSE,  '2024-05-01',  'A rechargeable electric toothbrush heads.',               'Health & Beauty', '/images/toothbrush.jpg', 'Electric Toothbrush', 'FIXED_PRICE'),
(TRUE, 99.99,   TRUE, FALSE,  '2024-05-05',  'A set of 6 luxury bath towels in assorted colors.',       'Home & Kitchen', '/images/towels.jpg', 'Luxury Bath Towel Set', 'FIXED_PRICE'),
(TRUE, 299.99,  TRUE, FALSE,  '2024-05-06',  'A high-definition smart LED TV.',                         'Electronics', '/images/tv.jpg', 'Smart LED TV', 'AUCTION'),
(TRUE, 49.99,   TRUE, FALSE,  '2024-05-02',  'A set of 4 ceramic dinner plates in a modern design.',    'Kitchen', '/images/plates.jpg', 'Modern Dinner Plate Set', 'FIXED_PRICE'),
(TRUE, 24.99,   TRUE, FALSE,  '2024-04-29',  'A pack of 8 scented candles in various fragrances.',      'Home Decor', '/images/candles.jpg', 'Scented Candle Set', 'FIXED_PRICE'),
(TRUE, 59.99,   TRUE, FALSE,  '2024-05-03',  'A stainless steel French press coffee maker.',            'Kitchen', '/images/coffee_maker.jpg', 'French Press Coffee Maker', 'AUCTION'),
(TRUE, 39.99,   TRUE, FALSE,  '2024-04-30',  'A set of 6 cotton kitchen towels in vibrant colors.',     'Home & Kitchen', '/images/kitchen_towels.jpg', 'Cotton Kitchen Towel Set', 'FIXED_PRICE'),
(TRUE, 79.99,   TRUE, FALSE,  '2024-05-07',  'A classic leather armchair with tufted upholstery.',      'Furniture', '/images/armchair.jpg', 'Tufted Leather Armchair', 'FIXED_PRICE'),
(TRUE, 34.99,   TRUE, FALSE,  '2024-05-01',  'A set of 12 fine-tip drawing pens.',                      'Stationery', '/images/drawing_pens.jpg', 'Fine-Tip Drawing Pen Set', 'FIXED_PRICE'),
(TRUE, 149.99,  TRUE, FALSE,  '2024-04-28',  'A portable Bluetooth speaker with built-in microphone.',  'Electronics', '/images/speaker.jpg', 'Bluetooth Speaker', 'AUCTION');

INSERT INTO OFFERS(OFFER_AMOUNT_MONEY,BUYER_ID,ID) VALUES ('10000','1','1'),('20000','3','2'),('30000','2','3');
INSERT INTO PRODUCTS_OFFERS(OFFERS_ID, PRODUCT_ID) VALUES ('1','3'),('2','2'),('3','1');
