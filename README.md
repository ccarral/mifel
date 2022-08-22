# Aplicación de prueba Banca Mifel 

[ci-badge]: https://github.com/ccarral/mifel/actions/workflows/CI.yaml/badge.svg
[ci-url]: https://github.com/ccarral/mifel/actions/workflows/CI.yaml
[![CI][ci-badge]][ci-url]  

Aplicación de prueba de un servicio REST, utilizando autenticación por OAuth2 y ORM
a través de la base de datos relacional H2 y CI con Github Actions. También expone
un servicio de encriptación que provisto de un mensaje y una clave privada (opcional) codificada
con base64 (url-safe) regresa el contenido encriptado en base 64.

## Autenticación
La aplicación utiliza el servicio de autenticación OAuth2 provisto por Github

## Ejecución

### Build
```bash
./mvnw build
```

### Ejecución con Docker
```bash
docker build -t ccarral/mifel .
docker run -p 8080:8080 \
  -e MIFEL_SECRET  -e GITHUB_CLIENT_SECRET \
  -e GITHUB_CLIENT_ID  \
  ccarral/mifel
```

## Servicios de usuario
Busca un usuario en una base de datos
### Buscar todos los usuarios
```
    GET "/api/mifel/usuario/"
```

### Buscar por nombre de usuario (indistinto a capitalización)
```
    GET "/api/mifel/usuario/nombre/carlos"
```
 #### Respuesta:
```json
{
  "usuarios": [
    {
      "id": 0,
      "nombre": "Carlos",
      "primerApellido": "Carral",
      "segundoApellido": "Cortés",
      "email": "carloscarralcortes@gmail.com"
    },
    {
      "id": 4,
      "nombre": "Carlos",
      "primerApellido": "Olivares",
      "segundoApellido": "Hernandez",
      "email": "carlosolivareshernandez@gmail.com"
    }
  ],
  "success": true,
  "count": 2
}
```
### Buscar por ID de usuario
```
    GET "/api/mifel/usuario/id/2"
```
```json
{
  "usuarios": [
    {
      "id": 2,
      "nombre": "James",
      "primerApellido": "McGill",
      "segundoApellido": "Juarez",
      "email": "jamesmcgilljuarez@gmail.com"
    }
  ],
  "success": true,
  "count": 1
}
```
## Servicio de Pokemon
Consume internamente la siguiente API `https://pokeapi.co/api/v2/pokemon/` y 
regresa un subconjunto de los datos.

```
    GET "/api/mifel/pokemon/pikachu"
```

```json
{
  "pokemon": {
    "id": 25,
    "abilities": [
      {
        "ability": {
          "name": "static",
          "url": "https://pokeapi.co/api/v2/ability/9/"
        },
        "slot": 1,
        "hidden": false,
        "is_hidden": false
      }
    ],
    "base_experience": 112,
    "held_items": [
      {
        "item": {
          "name": "oran-berry",
          "url": "https://pokeapi.co/api/v2/item/132/"
        },
        "version_details": [
          {
            "rarity": 50,
            "version": {
              "name": "ruby",
              "url": "https://pokeapi.co/api/v2/version/7/"
            }
          }
        ]
      }
    ]
  },
  "success": true
}
```
## Encripción de una cadena utilizando una clave (opcionalmente) provista por el usuario
La clave puede ser provista como un parámetro de URI codificada en url-safe base 64.
El vector de inicialización es provisto por la instancia `SecureRandom` por default de `java.security`.
En caso de que el parámetro `key` no sea provisto, el servicio utilizará una clave definida en un archivo `.env`
con la variable `$MIFEL_SECRET`.
```
    GET "/api/mifel/encripta/?msg="Hola mundo"
```
```json
{
  "success": true,
  "encryptedBase64": "AfNGM4+zRrcCU04Cryb+RQ=="
}
```
