
# Docs

### Save People

    /person

Example Request Body
```json
[
    {
        "name": "Name 1",
        "postalCode": "001"
    },
    {
        "name": "Name 2",
        "postalCode": "002"
    },
    {
        "name": "Name 3",
        "postalCode": "003"
    },
    {
        "name": "Name 10",
        "postalCode": "010"
    }
]
```

### Get People by Postal Code Range

Example Response Body
```json
{
    "people": [
        {
            "id": 1,
            "name": "Name 1",
            "postalCode": "001"
        },
        {
            "id": 4,
            "name": "Name 10",
            "postalCode": "010"
        },
        {
            "id": 2,
            "name": "Name 2",
            "postalCode": "002"
        },
        {
            "id": 3,
            "name": "Name 3",
            "postalCode": "003"
        }
    ],
    "totalNamesChars": 25
}
```
