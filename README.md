# Usuario API

API independiente para registrar usuarios, construida con Java 17, Spring
Boot, Maven y MongoDB.

## Ejecutar con Docker

```bash
docker compose up --build -d
```

La API queda disponible en `http://localhost:8081/api/usuarios`.

La aplicación utiliza el MongoDB instalado en el equipo:

```text
mongodb://localhost:27017/usuarios
```

Al ejecutar la API dentro de Docker, `host.docker.internal` permite acceder
al MongoDB de Windows sin contenerizar la base de datos.

## Endpoint de registro

```http
POST /api/usuarios
Content-Type: application/json
```

```json
{
  "nombre": "Ana Pérez",
  "correo": "ana@example.com",
  "clave": "secreto123"
}
```

## Endpoint de login

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "correo": "admin@example.com",
  "clave": "admin123"
}
```

Para detener la API:

```bash
docker compose stop
```
