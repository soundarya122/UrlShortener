
# URL Shortener app

- This application is created to shorten the big url to tiny url. 
- It would be easy to share and remember.
- This app uses H2 database.

- The output of the Shortened url is provided in **Hash** as well as **Incremental numbers**

## **The approach**
The basic requirement was to convert a big Url string into a smaller one. To achieve this, I have followed these steps:
1. I needed a database where the big url is stored along with it's shortened url reference. For the sake of convenience I used H2 database as it is easily embeded with my application and also provides the querying capabilities.
2. Using a third party library murmur3_32 which has readily available feature of converting a large chunk of data into small tables using hashing function.
3. Created a class which stores the original Url, the shortened url and the auto generated id.
4. Controllers created for:
    1. POST api to convert the original Url to shorted url and save in database.
    2. GET api to fetch Original url by query using Short url
    3. GET api to fetch Original url by query using Id
5. Created a simple HTML page which consumes the REST API to post and show the URLs.
6. List all the URLs entered.
7. If URL is already available in db, return the available short urls.



## **Assumptions**

This is an app which is run on local machine and the data is stored in the H2 database.
- Assuming following dependencies are installed on the system:
    1. Maven 3.8.6 or higher
    2. JDK 1.8 or higher
- On every restart of the web application, the data would be lost
- As client side validations are already applied, I have not applied validation of URL at the backend.
- Checked the output on the desktop only, CSS is not optimized for all resolutions.


## **Run Locally**

Clone the project

```bash
  git clone https://github.com/soundarya122/UrlShortener
```

Go to the project directory

```bash
  cd urlShortener
```

Run this command on console

```bash
  mvn spring-boot:run
```



## **Frontend**

It is using HTML with Javascript.
For the purpose of beautification, I have used Bootstrap.


The application runs at this URL:
http://localhost:8082/

- This page has an input field for entering URL. On click "Shorten", it calls the Rest API and returns the short url.
- Shortened URL for both **Hash** and **Number** are available.
- Validations applied on the client side for checking the valid URL.
- Opens the Url in new tab by clicking on any of the shortened url link
- List of all the Shortened Urls



## **API Reference**

#### Post new Url

```http
  POST http://localhost:8082/url/generate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `url` | `string` | Your original URL |



#### Get Original URL by entering Short url
- This uses Hashing

```http
  GET http://localhost:8082/url/{shortUtl}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `shortUtl`      | `string` | Short url |


#### Get Original URL by entering Id

```http
  GET http://localhost:8082/url/id/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | Id |

