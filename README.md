# TP REST
## Généralités sur Rest
### Le concept
Le terme ***REST (Representational State Transfer)*** représente une ***architecture logicielle*** qui définit ensemble deux contraintes pour la création de services .
Le ***client*** est la personne application frontale ou mobile qui utilise et consomme les ressources de l'API.
Les ***ressources*** sont les données ***exposés*** pas notre API.
<br/> Sur une API Rest les ***requêtes s'effectuent sur l'URL d'un ressource***, puis les ressources récupérées sous le format ***JSON, XML, ...***

### GET, POST, PUT, DELETE : le protocol HTTP
Les API REST sont basées sur HTTP, qui signifie Hypertext Transfer Protocol.
Les échanges sont basés sur les requètes du client à l'API via des requètes de différents types :
* ***GET :*** récupérer des données à partir d'une ressource.
* ***POST :*** envoyer des données à traiter à une ressources spécifiques
* ***PUT :*** mettre à jour une ressources spécifié
* ***DELETE :*** supprimer une ressources spécifié

Nous allons dans la suite du TP utiliser les différentes Méthodes.
## Mise en place d'un projet Rest avec JEE et Jersey avec un Tomcat
### Explications de l'architecture de l'application
L'API Rest que nous allons construire se présente sous la même forme que l'application Web que vous avez déjà créé au cours précédent.
Ici la seule différence sera dans le mapping des Servlet et dans leur contenu.
<br/><br/>***<span style="color:red"> ICI NOUS NE SOMME PLUS EN MVC : </span>*** Par conséquent plus de vue, donc plus de ***.jsp***.

### Récupération de l'application sous GitHub
Ici le but n'est pas de vous faire regénérer une application et d'y ajouter des dépendances car il existe un grand nombre d'alternatives possibles pour créer une API Rest sous JEE, il est donc cohérent ici de vous fournir un squellette fonctionnel.
Je vous invite donc à cloner la V1 du projet sous GitHub à cette URL :  <https://github.com/lcuoco/TPRest.git>
Une fois le projet cloner Nous allons pouvoir passer à l'étape suivante.

### Tour d'Horizon dans le projet
Après avoir cloné le projet ***TPRest***, vous vous retrouvez donc avec une API Rest initialisée.
Vous avez différents fichiers :
* ***pom.xml*** c'est le fichier qui permet de définir comment le projet est ***build*** et de quelles dépendences il a besoin, la première chose à faire est donc de récupérer ses dépendances et de regénérer le livrable à déployer en suivant la procédure suivante :
    * Si vous êtes sous Intellij :
      Cliquez sur vos configurations en haut gauche, ***Edit Configurations ...*** > ***+*** > ***Maven***, ici dans ***Work Directory***, entrez le chemin du projet et pour la ***Command Line*** tapez : ***clean install***, puis cliquez sur ***Apply*** et ***OK***, lancez ensuite la configuration.
    * Si vous êtes sous Eclipse : Ouvrez une console, rendez-vous à la racine du projet et lancez la commande ***mvn clean install***
      <br/> Ici vous avez bien récupéré toutes les sources du projet et ses dépendances.
* ***web.xml*** que vous connaissez déjà, qui décris la configuration de vos Servlet, ici nous n'avons qu'une seule Servlet de configuré. Le fichier se présente comme ci-après :
```xml 
    <servlet>
        <servlet-name>api</servlet-name>
        <servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>com.sun.jersey.config.property.packages</param-name>
            <param-value>fr.polytech.TPRest.Servlet</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>api</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
```
Comme dans l'application Web réalisé la séance précédente, ici il est configuré un mapping vers une Servlet sauf qu'ici on va définir un dossier dans lequel se trouvent toutes les Servlet faisant office de Service Web, car ici une servlet est un controlleur ayant pour vocation d'être utilisé comme un ***Service Web***, on va aussi choisir que c'est cette ***Servlet*** qui est prioritaire sur les autres et que les controleurs sont disponible à la racine de l'URL ***/***.
* ***HelloServlet.java*** la servlet de test pour comprendre les mécanismes de Rest :
```java
@Path("/hello")
public class HelloServlet extends HttpServlet {
    @GET
    @Path("sayHello")
    public String sayHello() {
        return "hello";
    }
}
```
***ICI ON UTILISE TOMCAT ET PAS TOMEE***<br/>
Après avoir crée une config Tomcat comme fait dans le TP précéndent, on voit ici une requète ***Get*** accesible à l'URL <http://localhost:8080/TPRest_war_exploded/hello/sayHello> (l'url change en focntion de votre configuration de tomcat), ici l'application renvéra bien une chaine de caractère ***hello*** <br/>
***Cet exemple permet d'introduire la syntaxe de création de service Web et dans la suite du TP nous aborderons d'autres méthodes et complexifierons la chose***

### Installation de SOAPUI ou Postman
Afin de pouvoir faire des requêtes à votre API REST il vous faut un logiciel.
Bien que les requêtes GET sont faciles à faire sur un navigateur, il devient difficile de faire les autres types de requêtes.
Nous vous laissons le choix sur le logiciel à utiliser.

### Création de la méthode pour une requête POST
Cette méthode va nous servir à créer une personne avec un nom et un prénom.
La classe Person est la pour ça. Ajoutez la à votre projet.
```java
public class Person {
    String firstName;
    String lastName;

    public Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person[firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}

```
Pour commencer il faut ajouter les annotations @POST et @Path.
Avant toute chose il faut ajouter une dépendance jersey dans le pom.xml qui gère le format Json.
Vous pourrez voir qu'il y a déjà des dépendances Jersey. C'est un framework qui permet de faire des requêtes REST.
```xml
<dependency>
    <groupId>com.sun.jersey</groupId>
    <artifactId>jersey-json</artifactId>
    <version>1.19</version>
</dependency>
```
Ensuite il faut donner à notre application l'init param dans le web.xml qui va nous permettre d'hydrater (créer) des objets Java à partir du Json.
```xml
<init-param>
    <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
    <param-value>true</param-value>
</init-param>
```
Ensuite on crée la méthode addPerson :
```java
@POST
@Path("post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addPerson(Person person) {
    return Response.ok().entity(person).cookie(new NewCookie("person", person.toString())).build();
}
```
Cette dernière prend en paramètre un objet Person (deux String : nom et prénom). Cet objet est généré grâce à jersey.
Ce sont les annotations @Consumes (format de l'objet récupéré) et @Produces (format de l'objet envoyé) qui indiquent à jersey de faire ces opérations.
Pour l'exemple on renvoie juste un cookie comprenant l'objet Person.

### Création de la méthode pour une requête GET
Cette méthode va renvoyer un objet Person.
En vous inspirant de la méthode addPerson, créez la méthode getPerson.
Pour l'exemple renvoyez simplement un objet Person, comprenant un nom et un prénom, créé dans la méthode.

### Création de la méthode pour une requête PUT
Cette méthode sert à modifier une personne.
Dans cet exemple on renvoie le même cookie que pour la requête POST. 
Mais il faut bien comprendre que les requêtes PUT servent à modifier des ressources ***déjà existantes***.

### Création de la méthode pour une requête DELETE
Cette méthode sert à supprimer une personne.
Ce type de requête sert à supprimer des ressources.
Comme nous n'avons pas de base de données pour l'exemple on va simplement renvoyer l'objet pour montrer que la reqûete a réussi.

## Voyons si vous avez compris (parce qu'avec la correction c'est facile :kissing: )
Maintenant que vous connaissez les bases de REST vous allez devoir effectuer trois choses :
1. Créer un service pour ajouter des objets de type Pokemon (champs : nom et niveau)
2. Créer un service pour récupérer tous les Pokemon
3. Créer un service pour modifier un Pokemon
4. Créer un service pour supprimer un Pokemon
