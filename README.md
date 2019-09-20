# SYM_Labo1
#### 1.Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut: anglais)?Que se passe-t-il si une traduction est manquantedans la langue par défaut ou dans une langue supplémentaire?
#### 2.Dans   l’exemple   fourni, sur   le   dialogue   pop-up, nous   affichons   l’icône android.R.drawable.ic_dialog_alert, disponible dans le SDK Androidmaisqui n’est pas très bien adapté visuellementà notre utilisation. Nous souhaitons la remplacer avec notre propre icône,  veuillez  indiquer  comment procéder.Dans  quel(s)  dossier(s)  devons-nous ajouter cette image?Décrivez brièvement la logique derrière la gestion des ressources de type «image»sur Android.Info: Googlemet à disposition des icônes open source dans le style «Material Design» utilisé actuellement sur Android:https://material.io/resources/icons/

#### 3.Lorsque le login est réussi, vous êtes censé chaîner une autre Activity en utilisant un Intent.Si je presse le bouton "Back" de l'interface Android, que puis-je constater? Comment faire pour que l'application se comporte de manière plus logique?Veuillez discuter de la logique derrière les activités Android.

#### 4.On pourrait imaginer une situation où cette secondeActivity fournit un résultat (par exemple l’IMEI ou uneautrechaîne de caractères) que nous voudrionsrécupérer dans l'Activity de départ. Comment procéder?

#### 5.Vous noterez que la méthode getDeviceId()du TelephonyManager,permettant d’obtenir l’IMEI du téléphone,est dépréciée depuis la version 26 de l’API.Veuillez discuter de ce que cela implique lors du développement et de présenterune façond’en tenir compte avec un exemple de code.

#### 6.Dans l’activité de login, en plaçant le téléphone (ou l’émulateur) en mode paysage(landscape), nous constatons que les 2 champs de saisie ainsi que le bouton s’étendent sur toute la largeur de l’écran. Veuillez réaliser un layout spécifique au mode paysagequi permet un affichage mieux adapté et indiquer comment faire pour qu’il soit utiliséautomatiquementà l’exécution.

#### 7.Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.

#### 8.Implémentezdans  votre  code  les  méthodes onCreate(), onStart(), onResume(), onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécutiondans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées.Vous  expliquerez  aussi  l’enchainement  de  ces  appels  lorsque  l’on  passe  d’une activité  à  l’autre. Comment  pouvez-vous  factoriser  votre  code  pour  éviter  de  devoir réimplémenter ces méthodes dans chacune de vos activités?

#### 9.Question Bonusfacultative-S’il vous reste du temps, nous vous conseillons de le consacrer à mettre en place la résolution des permissions au runtime.
