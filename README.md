# Docs

### Save People
Endpoint:

    /person

Example Request Body:
```json
[
    {
        "name": "Ard",
        "postalCode": "6000"
    },
    {
        "name": "Iris",
        "postalCode": "6001"
    },
    {
        "name": "Nick",
        "postalCode": "6002"
    }
]
```

### Get People by Postal Code Range
Endpoint:

    /person/postal-code-range?from={from}&to={to}

Example Endpoint:

    /person/postal-code-range?from=6001&to=6002
    
Example Response:
```json
{
    "people": [
        {
            "name": "Iris",
            "postalCode": "6001"
        },
        {
            "name": "Nick",
            "postalCode": "6002"
        }
    ],
    "totalNamesChars": 8
}
```
