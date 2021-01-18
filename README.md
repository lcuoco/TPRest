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

Nous allons dans la suite du TP utiliser les différentes Méthode.
## Mise en place d'un projet Rest avec JEE et Jersey avec un Tomcat
### Explications de l'architecture de l'application
L'API Rest que nous allons construire se présente sous la même forme que l'application Web que vous avez déjà créé au cours précédent.
Ici la seule différence sera dans le mapping des Servlet et dans leur contenu.
<br/><br/>***<span style="color:red"> ICI NOUS NE SOMME PLUS EN MVC : </span>*** Par conséquent plus de vue, donc plus de ***.jsp***.

### Récupération de l'application sous GitHub
Ici le but n'est pas de vous faire regénérer une application et d'y ajouter des dépendances car il existe un grand nombre d'alternatives possibles pour créer une API Rest sous JEE, il est donc cohérent ici de vous fournir un squellette fonctionnel.
Je vous invite donc à cloner la V1 du projet sous GitHub à cette URL :  <https://github.com/lcuoco/TPRest.git>
Une fois le projet cloner Nous allons pouvoir passer à l'étape suivante.

### Tour d'Horzion dans le projet
Après avoir cloner le projet ***TPRest***, vous vous retrouvez donc avec une API Rest initialisé.
Vous avez différents fichiers différents :
 * ***pom.xml*** c'est le fichier qui peremt de définir comment le projet est ***build*** et de quelles dépendences il à besoin, la première choses à faire est donc de récupérer ses dépendances et de regénérer le livrable à déployer en suivant la procédure suivante :
     * Si vous êtes sous Intellij :
        Cliquez sur vos configurations en haut gauche, ***Edit Configurations ...*** > ***+*** > ***Maven***, ici dans ***Work Directory***, entrez le chemin du projet et pour la ***Command Line*** tapez : ***clean install***, puis cliquez sur ***Apply*** et ***OK***.
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
    public String deletePerson() {
        return "hello";
    }
}
```
On voit ici une requète ***Get*** accesible à l'URL <localhost:8080/hello/sayHello>, ici l'application renvéra bien une chaine de caractère ***hello***
***Cette exemple permet d'introduire la syntaxe de création de service Web et dans la suite du TP nous aborderons d'autres méthodes et complexifierons la chose***