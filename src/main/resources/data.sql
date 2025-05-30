

INSERT INTO products (id, name, sales_units) VALUES
                                                 (1, 'V-NECH BASIC SHIRT', 100),
                                                 (2, 'CONTRASTING FABRIC T-SHIRT', 50),
                                                 (3, 'RAISED PRINT T-SHIRT', 80),
                                                 (4, 'PLEATED T-SHIRT', 3),
                                                 (5, 'CONTRASTING LACE T-SHIRT', 650),
                                                 (6, 'SLOGAN T-SHIRT', 20);

INSERT INTO stock (product_id, size, quantity) VALUES
                                                   (1, 'S', 4), (1, 'M', 9), (1, 'L', 0),
                                                   (2, 'S', 35), (2, 'M', 9), (2, 'L', 9),
                                                   (3, 'S', 20), (3, 'M', 2), (3, 'L', 20),
                                                   (4, 'S', 25), (4, 'M', 30), (4, 'L', 10),
                                                   (5, 'S', 0), (5, 'M', 1), (5, 'L', 0),
                                                   (6, 'S', 9), (6, 'M', 2), (6, 'L', 5);
