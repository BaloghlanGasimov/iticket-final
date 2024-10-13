# iticket-final

This project uses JDK 17, Gradle 8.8, and Spring Boot 2.7.14.

## Building the Docker Image

To build the Docker image, use the following command:



```bash
docker compose up -d
```

>You can run the Project with different profiles.
>* DEV 
>* PROD
>
> by setting the ENV environment variable.

## Endpoints

#### Overview
The `X-Application-Name` header is used to identify the application or client making the request. This header is essential for routing, monitoring, or logging requests based on the application or client that generated them.

#### 1.1  Company Primary Saving


- **Method:** POST
- **URL:** `{{base_url}}/api/v1/companies/primary`
- **Headers:**
    - `X-Application-Name`: 
- **Body:**
  ```json
  {
      "name": "",
      "email": "",
      "logoImage": ""
  }
- **Response:**
  ```json
  {
    "success": true,
    "message": "Operation successful",
    "messageId": "2918ee96-5db6-4bf7-9550-af4f835d2e64",
    "data": {
        "id": 4,
        "name": "name",
        "email": "email",
        "website": null,
        "description": null,
        "status": "ACTIVE",
        "createdAt": "2024-09-06T09:53:24.505032",
        "updatedAt": null,
        "logo": {
            "id": 4,
            "name": "name",
            "bucket": "bucketName",
            "path": "http://127.0.0.1:9000/company/nature_test%285%29_20240906_095323.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=baloghlan%2F20240906%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240906T055324Z&X-Amz-Expires=36000&X-Amz-SignedHeaders=host&X-Amz-Signature=0fbef786bbcbdd755fc8c0033ab0e164a134fd783523d4953c476665b523aa16",
            "createdAt": "2024-09-06 09:53:24.419479"
        },
        "phones": null,
        "events": null
    }
}
### Field Descriptions

| Field Name           | Type          | Description                    | Example                       | Mandatory |
|----------------------|---------------|--------------------------------|-------------------------------|-----------|
| `name`               | String        | Name for the user.             | `Baloghlan`                   | Yes       |
| `email`              | String        | The unique email for the user. | `baloghlangasimovv@gmail.com` | Yes       |
| `logoImage`          | MultipartFile | File,image for the user.       | `test.jpg`                    | Yes       |


#### 1.2  Company Updating


- **Method:** PUT
- **URL:** `{{base_url}}/api/v1/companies/{id}`
- **Headers:**
  - `X-Application-Name`:
- **Body:**
  ```json
  {
      "name": "",
      "email": "",
      "logoImage": "",
      "website": "string",
      "description": "string",
      "phones": [
          {
             "phone": "string"
          }
      ]
  }
- **Response:**
  ```json
  {
    "success": true,
    "message": "Operation successful",
    "messageId": "2918ee96-5db6-4bf7-9550-af4f835d2e64",
    "data": {
        "id": 4,
        "name": "name",
        "email": "email",
        "website": null,
        "description": null,
        "status": "ACTIVE",
        "createdAt": "2024-09-06T09:53:24.505032",
        "updatedAt": null,
        "logo": {
            "id": 4,
            "name": "name",
            "bucket": "bucketName",
            "path": "http://127.0.0.1:9000/company/nature_test%285%29_20240906_095323.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=baloghlan%2F20240906%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240906T055324Z&X-Amz-Expires=36000&X-Amz-SignedHeaders=host&X-Amz-Signature=0fbef786bbcbdd755fc8c0033ab0e164a134fd783523d4953c476665b523aa16",
            "createdAt": "2024-09-06 09:53:24.419479"
        },
        "phones": null,
        "events": null
    }
}
### Field Descriptions

| Field Name    | Type          | Description                       | Example                               | Mandatory |
|---------------|---------------|-----------------------------------|---------------------------------------|-----------|
| `name`        | String        | Name for the company.             | `Baloghlan`                           | Yes       |
| `email`       | String        | The unique email for the company. | `baloghlangasimovv@gmail.com`         | Yes       |
| `logoImage`   | MultipartFile | File,image for the company.       | `test.jpg`                            | Yes       |
| `website`     | String        | Website url for the company.      | `www.test.com`                        | No        |
| `description` | String        | Desctiption for the company.      | `this is for description for company` | No        |
| `phones`      | String[]      | Phones for the company.           | [`0121234567`,`0551234567`]           | No        |

  ```

  ```
  
  


# Status Description for Service Status

This status defines the various states that a service can be in during its lifecycle. 
Below is a detailed explanation of each status value:

### Status Values

- **IN_PROGRESS**:Indicates that the process is still ongoing. If the status is `IN_PROGRESS`, you should continue polling until a final state is reached.

- **CREATED**: The service has successfully created the necessary resources (e.g., an ADOC file has been successfully created). This status confirms that the creation process is complete.

- **AUTHENTICATED**: Returned when authentication is successful, without any additional steps required. This status indicates that the authentication process has been completed and validated.

- **TRANSACTION_EXPIRED**: The transaction has expired, meaning it is no longer valid.This status is typically returned when the operation has timed out or exceeded the allowed duration.

- **CANCELED**: The process has been canceled by the user. If this status is returned, it indicates that the user has actively stopped the process.

- **SERVICE_NOT_AVAILABLE**: This status is returned when the service is not available because the third-party client has not responded. It indicates that an external dependency failed to provide the necessary response.

- **ERROR**: Represents an internal error within the system. If this status is encountered, it indicates a failure that is not related to external services or user actions.
