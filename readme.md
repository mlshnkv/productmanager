Тестовое задание:
    С помощью любой из технологий, указанных в описании к вакансии (Node.js, .Net core, Java), реализовать приложение, следующее Restподходу создания API. 
    Приложение позволяет создавать информацию о продуктах компании, а также информационные статьи по её продуктам, данные должны храниться в базе данных (выбор БД на ваше усмотрение, можно воспользоваться встраиваемой SQLite)
    
    
Приложение должно предоставлять два контроллера:
*	Products - должен содержать эндпоинты для CRUD - операций с данными о продуктах компании. В ответе на запрос продукта(ов), должны содержаться статьи, относящихся к этому продукту, а также должна быть возможность отфильтровать/отсортировать продукты по определенному полю.
*	Articles - должен содержать эндпоинты для CRUD - операций со статьями по продуктам, в ответе на запрос статьи(ей), должна содержаться полная информация о продукте, к которому она относится, а также должна быть возможность отфильтровать/отсортировать статьи по определенному полю.

Модель данных статьи:
*	идентификатор (id)
*	идентификатор продукта, который описывает данная статья(productid)
*	название
*	контент
*	дата создания


Модель данных продукта:
*	Идентификатор(id)
*	Название
*	Описание
*	Стоимость внедрения

-----------------------------

### curl commands:

###### Get a list of products

`curl --location --request GET 'http://localhost:8080/products'`

###### Create product

`curl --location --request POST 'http://localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "MDM Mulitdomain",
        "description": "MDM создает достоверную информацию.",
        "price": 1000
    }'`

###### Get a list filtered by price and sorted by name

`curl --location --request GET 'http://localhost:8080/products?order=NAME&minPrice=400&maxPrice=700'`



###### Update product

`curl --location --request PUT 'http://localhost:8080/products/100000' \
--header 'Content-Type: application/json' \
--data-raw '{"id": 100000,
    "name": "Big Data Management",
    "description": "Обновленное Описание Big Data Management",
    "price": 2000
}'`

###### Get a list of articles

curl --location --request GET 'http://localhost:8080/articles'

###### Create article

`curl --location --request POST 'http://localhost:8080/articles' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "НОВАЯ СТАТЬЯ О Data Preparation",
        "content": "Это вторая стать о Data Preparation",
        "product":{"id":100005}
    }'`

###### Update article

`curl --location --request PUT 'http://localhost:8080/articles/100009' \
--header 'Content-Type: application/json' \
--data-raw '{"id": 100009,
        "name": "ОБНОВЛЕННАЯ Статья о Relate 360",
        "content": "Это Обновленная стать о Relate 360"
}'`

###### Get a list filtered by product id and sorted by date

`curl --location --request GET 'http://localhost:8080/articles?order=DATE&productId=100002'`