# Transaction Statistics API

Stores transaction requests and retrieves the statistics of the ones that were sent during the last 60 seconds

## Specs

API includes two different endpoints

### POST /transactions

Example request body

```
{
    "amount": 12.3,
    "timestamp": 1478192204000
}
```

where amount is transaction value and timestamp is transaction time in UTC time zone

### GET /statistics

Example response body

```
{
    "sum": 1000,
    "avg": 100,
    "max": 200,
    "min": 50,
    "count": 10
}
```

where sum is the total amount, avg is the average amount, max is the highest amount, min is the 
lowest amount and the count is number of transactions

## Run With

```
mvn spring-boot:run
```

## Test With

Runs all unit and integration tests


```
mvn clean install
```
