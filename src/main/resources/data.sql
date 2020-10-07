DELETE
FROM article;
DELETE
FROM product;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO product (name, description, price)
VALUES ('Big Data Management',
        'Золотой стандарт решений для интеграции больших данных с возможностью быстрого и гибкого анализа.', 100),
       ('Relate 360',
        'Реализуйте целостный подход к управлению качеством всех ваших данных независимо от размера, формата или платформы.',
        200),
       ('Enterprise Data Preparation',
        'Используйте новые инновационные подходы анализа данных и принимайте бизнес-решения быстрее, перейдя к централизованному управлению источниками данных.',
        300),
       ('Enterprise Data Catalog', '"Умный" каталог данных.', 400),
       ('Axon Data Governance', 'Решение для управления данными.', 500),
       ('Big Data Streaming',
        'Обеспечьте обработку данных потоковых процессов с помощью ввысокоскоростной обработки событий и многократного использования разработанных процессов.',
        600);

INSERT INTO article(name, content, created, product_id)
VALUES ('Статья1 о Big Data Management', 'Это первая стать о Big Data Management', NOW() - 2 DAY, 100000),
       ('Статья2 о Big Data Management', 'Это вторая стать о Big Data Management', NOW() - 1 DAY, 100000),
       ('Статья3 о Big Data Management', 'Это третья стать о Big Data Management', NOW(), 100000),
       ('Статья1 о Relate 360', 'Это первая стать о Relate 360', NOW() - 1 DAY, 100001),
       ('Статья2 о Relate 360', 'Это вторая стать о Relate 360', NOW(), 100001),
       ('Статья1 о Enterprise Data Preparation', 'Это первая стать о Enterprise Data Preparation', NOW() - 1 DAY, 100002),
       ('Статья2 о Enterprise Data Preparation', 'Это вторая стать о Enterprise Data Preparation', NOW(), 100002),
       ('Статья1 о Axon Data Governance', 'Это первая стать о Axon Data Governance', NOW() - 5 DAY, 100003),
       ('Статья2 о Axon Data Governance', 'Это вторая стать о Axon Data Governance', NOW() - 6 DAY, 100003),
       ('Статья1 о Big Data Streaming', 'Это первая стать о Big Data Streaming', NOW() - 4 DAY, 100004),
       ('Статья2 о Big Data Streaming', 'Это вторая стать о Big Data Streaming', NOW(), 100004);