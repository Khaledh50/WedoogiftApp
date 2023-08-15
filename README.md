
### Wedoogift Backend Application

Cette application backend fournit des services aux entreprises pour distribuer des dépôts de cadeaux et de repas aux utilisateurs, 
tout en calculant également le solde de l'utilisateur en fonction des dépôts donnés. 
L'application est construite en utilisant Spring Boot, en faisant d'elle une solution fiable et évolutive.
Cette application backend fournit des services aux entreprises pour distribuer des dépôts de cadeaux et de repas aux utilisateurs, 
tout en calculant également le solde de l'utilisateur en fonction des dépôts donnés. 
L'application est construite en utilisant Spring Boot, en faisant d'elle une solution fiable et évolutive.


### Table of Contents

Features
Setup
API Endpoints
Usage
Contributing
License

### Features

### Dépôts de Cadeaux

Les dépôts de cadeaux ont une durée de vie de 365 jours à partir de la date de distribution.
Après l'expiration de la période de 365 jours, les dépôts de cadeaux ne seront plus pris en compte dans le solde de l'utilisateur.

### Dépôts de Repas

Les dépôts de repas fonctionnent de manière similaire aux dépôts de cadeaux, 
mais ils expirent à la fin du mois de février de l'année suivant la date de distribution.

### Setup

1. Exécution de l'application
2. Clonez ce référentiel sur votre machine locale.
3. Ouvrez une invite de commande ou un terminal dans le répertoire racine du projet.
4. Exécutez la commande suivante pour lancer l'application ou a partir de votre editeur de texte:

**`mvn spring-boot:run`**

Une fois l'application lancée, vous pouvez accéder à la base de données H2 pour consulter les données.

**`http://localhost:8887/h2-console/login.jsp`**

pour Username et Password ils existent deja dans le fichier l'application properties.

### Utilisation de l'application

Une fois l'application lancée, vous pouvez accéder à l'interface utilisateur via l'URL 
http://localhost:8887. 
Vous pourrez alors voir gifts et deposits disponibles et calculer le balance associée aux Dépôts de Repas et Dépôts de Cadeaux pour un utilisateur.

### Choix techniques

Spring Boot : J'ai choisi Spring Boot pour simplifier le développement de l'application et bénéficier de ses fonctionnalités intégrées.
Junit:pour faire les tests unitaires.
Base de données H2 en mémoire : Pour faciliter le déploiement et les tests, j'ai opté pour une base de données H2 en mémoire qui ne nécessite pas de configuration de serveur de base de données.
Spring Data JPA : J'ai utilisé Spring Data JPA pour faciliter la couche d'accès aux données et les opérations de persistance.
Lombok : J'ai utilisé le plugin Lombok pour simplifier la création de classes POJO en générant automatiquement les getters, setters et autres méthodes utiles.

#### Auteur
Haddad Khaled

Licence
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser et de le modifier selon vos besoins.