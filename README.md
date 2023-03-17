# Development

- You can install openjdk17 in your machine OR
- Use [dev container](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)

# Quick Start
```bash
docker-compose up 
# or
./mvnw spring-boot:run
```

# Test
```bash
./mvnw test --batch-mode
```

# API Document

## Encrypt Plain Text

```
POST /aes/encrypt
```

### Request Body

| Key          | Type     | Description                 |
| :----------- | :------- | :-------------------------- |
| `aes_key`    | `string` | the length must equal to 32 |
| `plain_text` | `string` | String to encrypt           |

### API Responses

```ts
{
    "cipher_text": string
}
```

## Decrypt Cipher Text

```
POST /aes/decrypt
```

### Request Body

| Key           | Type     | Description                 |
| :------------ | :------- | :-------------------------- |
| `aes_key`     | `string` | the length must equal to 32 |
| `cipher_text` | `string` | String to decrypt           |

### API Responses

```ts
{
    "plain_text": string,
    "base64": string
}
```
