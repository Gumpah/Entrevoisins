# Bienvenue !

Bienvenue sur le GitHub de l'application **Entrevoisins**. Vous trouverez ci-dessous toutes les instructions pour télécharger le code de l'application, la compiler, et la lancer sur un appareil.


## Téléchargement du code

### Première étape

Avant de démarrer, je vous invite à télécharger l'application Android Studio, sans laquelle vous ne pourrez pas vous rendre plus loin !
Pour ce faire, rendez-vous sur la page de l'application : [clique sur moi !](https://developer.android.com/studio)

Une fois sur la page, cliquez sur le bouton "Download Android Studio" encadré en rouge sur l'image qui suit :

<details>
  <summary>Voir l'image</summary>

   ![android studio download](https://i.imgur.com/lqxLgWr.png)
   
</details>

Acceptez les conditions, enregistrez le fichier, et exécutez-le. Après avoir suivi les instructions d'installation, vous devriez avoir Android Studio sur votre ordinateur.

### Troisième étape

Une fois revenu ici, copiez le lien du dépôt GitHub :

<details>
  <summary>Voir les étapes</summary>

   ![etapes copie lien](https://i.imgur.com/bsUlIh4.png)
   
</details>

1 - Cliquez sur le bouton vert **Code**  
2 - S'il n'est pas séléctionné par défaut, séléctionnez l'onglet **HTTPS**  
3 - Cliquez sur l'icône à droite du lien afin de copier ce dernier  

### Quatrième étape

Ouvrez maintenant Android Studio. Sur la fenêtre d'accueil cliquez sur **Get from Version Control** :
<details> 
    <summary>Voir l'image</summary> 

   ![android studio download](https://i.imgur.com/d9RAlwo.png)
   
</details>

Vous devriez atterrir sur cette fenêtre ;
<details>
    <summary>Voir l'image</summary>
    
   ![android studio download](https://i.imgur.com/q8mizQa.png) 
   
</details>

1 - Collez le lien GitHub (précédemment copié) dans la case **URL**
2 - Dans votre l'explorateur de fichiers, choisissez un dossier vide qui contiendra le projet
3 - Cliquez ensuite sur **Clone** pour valider


Et voilà, le projet est désormais sur votre ordinateur.


## Lancez l'application

Pour lancer l'application, vous avez deux possibilités. La lancer sur un émulateur directement sur votre ordinateur, ou la lancer sur un appareil physique en le branchant à votre ordinateur.

<details>
    <summary>Créer un émulateur</summary>
  
 Sur Android Studio, rendez-vous en haut de votre écran, dans le menu **Tools**, sélectionnez **AVD Manager** :
  ![emulator example](https://i.imgur.com/7hv6lSC.png)
  
  Cliquez ensuite sur **Create Virtual Device** en bas à gauche de la fenêtre
  
  
  Choisissez le modèle qui vous souhaitez, puis cliquez sur **Next**
  ![emulator example 3](https://i.imgur.com/s4dmPXi.png)
  
  Téléchargez ensuite la version d'Android de votre choix en cliquant sur **Download**. Une fois le téléchargement terminé, cliquez sur **Finish**. Sélectionnez la version téléchargée, puis cliquez sur **Next**.
  ![emulator example 4](https://i.imgur.com/RxdeV4a.png)
  
  La dernière fenêtre vous offre certaines options, si vous ne souhaitez rien modifier, cliquez sur **Finish**.
  
  Voilà, vous venez de créer votre émulateur.
</details>

Si vous souhaitez lancer l'application sur un appareil physique, il vous suffit de le brancher avec le mode **Débogage** activé.

Vérifiez que c'est bien **app** qui est sélectionné dans le fenêtre de gauche, sélectionnez ensuite soit votre émulateur, soit votre appareil, puis cliquez sur le bouton vert de lancement :  
![emulator example 5](https://i.imgur.com/zM1wJ50.png)  
L'application se lance ensuite automatiquement sur le dispositif sélectionné.
