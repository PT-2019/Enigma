# Enigma

### Créez vos propres escape games, et jouez-y seuls ou à plusieurs dans ENIGMA !

![scenario](https://user-images.githubusercontent.com/54904135/68858458-ffa81a00-06dc-11ea-81f4-b9a3deeb9ecb.png)

Auteurs

* Louka DOZ
* Quentin RAMSAMY-AGEORGES
* Loïc SENECAT
* Jorys-Micke ALAIS

Crée le 03 octobre 2019

---

L'application comprends :
* Un éditeur permettant de créer des escapes games puis de les exporter
* Un lanceur permettant de configurer une partie (solo ou multi-joueur) et
d'y jouer.

Des détails plus importants se trouvent dans le WIKI.

---

MapsNameUtils contient des constantes pouvant être utilisés par le créateur
pour ajouter des propriétés a la map.

# Entity

GameObject représente n'importe quelle entité du jeu et également
des ensembles d'entités comme les pièces.

Entité représente toutes les être vivants mais aussi les objects.

Item représente la sous catégorie d'entité qui sont des objects.

### Convenience

AbstractItem est une classe pratique pour ne pas devoir réécrire
les méthodes de base de item.

AbstractGameObject est une classe pratique pour ne pas devoir réécrire
les méthodes de base de GameObject.

# Entity utils

IDInterface : un id pour les énigmes

Activatable : un item qui peut être activé 

### EnigmaJsonReader, Writer

Lis un fichier contenant une sauvegarde des enigmes (json) et sauvegarde les enigmes.

# Utils

Bounds représente un rectangle mais plus précisément 4 sommets.

CheckEventType vérifie le type d'évènement pour la libgdx.

InputAdapter est une classe qui permet d'éviter de devoir implémenter
toutes les méthodes liées à l'input.

LoadLibgdxGameApplication charge l'application libgdx dans un composant
swing.

Utility propose des méthodes pour faciliter le travail du programmeur.

# Enums

Attributes : Attributs des champs de la sauvegarde des énigmes

Direction : les 4 directions

EntitiesCategories : catégories d'entités pour le menu (trier les entités
affichés par catégorie)

Layer : Les niveaux de la map