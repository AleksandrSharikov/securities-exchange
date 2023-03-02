-- Заполнить таблицу "Tariff"
INSERT INTO public.tariff (title, tariffCost description, type, is_active)
VALUES ('Подписка на 14 дней', 100, 'Каталог акций и облигаций', 'permanent', true),
       ('Подписка на 1 месяц', 190, 'Каталог акций и облигаций', 'permanent', true),
       ('Подписка на 1 квартал', 400, 'Каталог акций и облигаций', 'permanent', true),
       ('Подписка на 1 год', 1200, 'Каталог акций и облигаций', 'permanent', true)