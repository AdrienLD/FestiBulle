# FestiBulle API

## Description
FestiBulle est une API de gestion d'événements festifs. Elle permet aux utilisateurs de créer, gérer et participer à des soirées, ainsi que d'interagir avec d'autres utilisateurs via des conversations et des avis.

## Fonctionnalités principales
- Gestion des utilisateurs (inscription, connexion, profil)
- Création et gestion de soirées
- Système de messagerie
- Système d'avis sur les utilisateurs et les soirées
- Recherche de soirées

## Technologies utilisées
- Java
- Spring Boot
- Spring Security (JWT pour l'authentification)
- Spring Data JPA
- Swagger pour la documentation de l'API

## Configuration requise
- Java 17 
- Maven

## Installation et démarrage

### Avec IntelliJ IDEA (méthode recommandée)
1. Ouvrez IntelliJ IDEA
2. Sélectionnez "File" > "Open" et naviguez jusqu'au dossier du projet FestiBulle
3. Une fois le projet ouvert, IntelliJ devrait automatiquement détecter qu'il s'agit d'un projet Maven et le configurer
4. Trouvez la classe `FestiBulleApplication.java` dans l'arborescence du projet
5. Cliquez droit sur cette classe et sélectionnez "Run 'FestiBulleApplication'"

L'API sera accessible à l'adresse : `http://localhost:8081`

## Documentation de l'API
La documentation Swagger de l'API est disponible à l'adresse : `http://localhost:8081/swagger-ui.html`

## Endpoints de l'API

### Soirée
API de gestion des soirées

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/soirees/{id}` | Récupérer les détails d'une soirée spécifique |
| PUT | `/api/soirees/{id}` | Modifier une soirée |
| DELETE | `/api/soirees/{id}` | Supprimer une soirée |
| GET | `/api/soirees` | Récupérer la liste des soirées |
| POST | `/api/soirees` | Créer une nouvelle soirée |
| POST | `/api/soirees/recherche` | Rechercher des soirées |
| GET | `/api/soirees/{id}/participants` | Liste des participants à une soirée |
| GET | `/api/soirees/mes-soirees` | Liste des soirées organisées par l'utilisateur connecté |
| GET | `/api/soirees/mes-participations` | Liste des soirées auxquelles l'utilisateur participe |

### Avis
API de gestion des avis

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| PUT | `/api/avis/{id}` | Modifier son avis |
| DELETE | `/api/avis/{id}` | Supprimer son avis |
| POST | `/api/avis/utilisateurs/{id}` | Ajouter un avis sur un utilisateur |
| GET | `/api/avis/soirees/{id}` | Récupérer les avis d'une soirée |
| POST | `/api/avis/soirees/{id}` | Ajouter un avis sur une soirée |

### Adresse
API de gestion des adresses

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/v1/adresses` | Récupérer toutes les adresses avec pagination |
| POST | `/api/v1/adresses` | Créer une nouvelle adresse |
| GET | `/api/v1/adresses/{id}` | Récupérer une adresse par son ID |
| GET | `/api/v1/adresses/ville/{ville}` | Récupérer les adresses par ville |
| GET | `/api/v1/adresses/region/{region}` | Récupérer les adresses par région |

### Conversation
API de gestion des conversations

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/conversations` | Liste des conversations de l'utilisateur |
| POST | `/api/conversations` | Création d'une nouvelle conversation (pour les conversations 1-1) |
| POST | `/api/conversations/{id}/messages` | Envoi d'un message dans une conversation |
| GET | `/api/soirees/{id}/conversation` | Récupération de la conversation de groupe d'une soirée |
| GET | `/api/conversations/{id}` | Récupération des messages d'une conversation |

### Utilisateur
API de gestion des utilisateurs

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/v1/utilisateurs/{id}` | Récupérer un utilisateur par son ID |
| PUT | `/api/v1/utilisateurs/{id}` | Mise à jour du profil de l'utilisateur connecté |
| DELETE | `/api/v1/utilisateurs/{id}` | Suppression du compte de l'utilisateur connecté |
| GET | `/api/v1/utilisateurs` | Récupérer tous les utilisateurs avec pagination |
| GET | `/api/v1/utilisateurs/profile` | Récupérer un utilisateur par son ID |

### Authentification

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/auth/register` | Inscription d'un nouvel utilisateur |
| POST | `/api/auth/login` | Connexion d'un utilisateur |

## Sécurité
L'API utilise JSON Web Tokens (JWT) pour l'authentification. Un token JWT valide doit être inclus dans le header `Authorization` (bearer) pour accéder aux endpoints protégés.

