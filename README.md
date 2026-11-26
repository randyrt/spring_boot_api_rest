# Demo Spring Boot

L'application Spring Boot s'exécute **localement**. Seuls MariaDB et phpMyAdmin
sont lancés dans Docker.

## Prérequis

- Java 21
- Docker et Docker Compose

## Démarrage

Depuis la racine du projet :

```bash
docker compose up -d mariadb phpmyadmin
./mvnw spring-boot:run
```

Sous Windows, utiliser `mvnw.cmd` à la place de `./mvnw`.

L'application est disponible sur http://localhost:8080 et phpMyAdmin sur
http://localhost:8081. Utiliser `localhost` dans le navigateur plutôt que
`0.0.0.0`.

## Authentification

Les endpoints de l'application nécessitent un JWT. Par défaut, les identifiants
de développement sont `admin` / `admin123`.

Obtenir un token :

```bash
curl -X POST http://localhost:8080/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin","email":"admin@example.com","password":"admin123"}'
```

Puis appeler `/hello` avec la valeur du champ `token` retourné :

```bash
curl http://localhost:8080/hello \
  -H 'Authorization: Bearer VOTRE_TOKEN'
```

Les identifiants peuvent être modifiés avec les variables d'environnement
`APP_USERNAME` et `APP_PASSWORD`.

## Connexion à la base

La configuration locale par défaut est déjà définie dans
`src/main/resources/application.properties` :

- hôte : `localhost`
- port : `3306`
- base : `spring_db`
- utilisateur : `spring_user`
- mot de passe : `spring_password`

Dans phpMyAdmin, utiliser le serveur `mariadb` avec ces mêmes identifiants.

## Arrêt

```bash
docker compose down
```

Les données MariaDB sont conservées dans le volume Docker `mariadb_data`.
