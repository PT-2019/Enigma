# EDITOR

Il s'agît de la partie éditeur de map (créateur) de Enigma.

![Capture d’écran de 2019-12-09 19-41-28](https://user-images.githubusercontent.com/54904135/70462803-ee73e280-1abb-11ea-8845-9e1eaa5a7fca.png)

La map est chargée avec la libgdx. (code de chargement est déjà codé, code de la map déjà codé, il faut placer dans la zone la map).

# Menus

Afficher les menus
- **File** Ouvrir, Sauvegarder, Exporter la map
- **Edit** Redo, undo possibles
- **Run** Lance un test du jeu. Mode console possible
- **Help** Contient des aides/manuels du logiciel

# Barre d'outils

La barre d'outils
- raccourcis pour respectivement charger, sauvegarder, undo et redo.
- elle permet de charger la souris : normal ou gomme
- le truc avec les flèches dans les quatre sens c'est pour déplacer la map
- elle permet d'ajuster le zoom, options fit (toute la map dans l'écran) et in-game (zoom du jeu)

# Catégories

Le menu catégories affiche toutes les entités de la catégorie. Il s'agît de Label contenant une ImageIcon. Cette image icon est une classe a créer car elle contient une information en plus (cf - ImageIcon).
Les images icon affichés sont celles des entités dont le champs (a créer) getCatégorie renvoi la même catégorie que celle sélectionnée.

## ImageIcon - EntityIcon.java (extends ImageIcon) par exemple
Après longue et très courte réflexion. Il est sûrement mieux que les entités (salles, ...) soient, dans l'éditeur au moins représentés par une image .png fixe. Cependant, elle doit contenir plus d'informations. Je propose une json de la forme suivante
```
src.editor.entity.Porte : { //pour une porte
   path: chemin/vers/.png
   tiles: { //tableau des tiles
      layer0: {0,0,0,0}
      layer1: {0,1,1,0}
      layer2: {...}
      ...
   }
}
```
Une entity factory prendrait un json (ou des jsons) et créerait les entités correspondantes.(newInstance("src.editor.entity.Porte.java") ou un truc du genre).

**DONC EN RESUME** : une entité est une image (donc 1 porte = 1 image de porte) mais contiennent les ids des tiles qui la composent.

# Drag and drop

On peut déplacer les entités (label contenant "ImageIcon") vers la map. Au drop, on lit dans le label sont object (EntityIcon.java par exemple) et nottament on appelle une méthode (ex: getTiles ou getTexture) qui renvoi les tiles représentant l'entités pour chaque niveau.

On assigne ensuite les tiles correspondants sur la map. On peut lever un popup d'erreur si déjà des tiles de prises là ou on a placé. Attention, si erreur alors aucune tile n'est placée. On peut par exemple vérifier que toutes les cases sont vides avant de placer une room ou s'il y a une room avant de placer une entité. (on pourra créer une interface avec les méthode getTiles/getTexture et needRoom()).

https://gamedev.stackexchange.com/questions/111495/referencing-individual-cells-of-a-tilemap-and-changing-texture

On doit également sauvegarder dans une structure dynamique (dans le programme) que c'est l'entité X qui a été placée donc on a par exemple une Hashmap d'entités. Cela permettra d'ouvrir le bon menu lorsque l'on clique dessus.

# Zoom

On peut zoomer/dézoomer la carte avec la molette. On peut également le faire avec un/des boutons dans la barre d'outils.
Le bouton fit que toute la carte tiens dans son écran. La check-box in-game fait que le zoom devient le même qu'en jeu. (in-game et fit ne peuvent être cochés en même temps)

# Clic sur une entité

Une fois placée sur la map, on peut cliquer sur n'importe quelle case. La map va regarder si ya des entités sur cette case (donc on a conservé un rectangle que a x,y de taille width, height) il y a l'entité par exemple Door.

Si le clic est gauche, alors elle ouvre un menu du style

![Capture d’écran de 2019-12-09 20-42-11](https://user-images.githubusercontent.com/54904135/70466886-7362fa00-1ac4-11ea-8eb7-5f0359ddabe4.png)

Il peut y avoir plus ou moins de contenu selon l'entité (prévoir ça dans l'interface parlée dans drag and drop par exemple).
* pour un object de l'interface Content on doit pouvoir ajouter du contenu, en retirer...
* Pour un personnage, on devrait pouvoir lui donner un nom, définir si c'est un héro (personnages utilisés par les joueurs et leur numéro si c'est le cas (j1, j2, j3, j4, ...)
* si c'est un passage, on peut définir si sa salle de droite ou de gauche doit être cachés tant que la porte de l'autre côté n'as pas été ouverte. (si je cache la salle a gauche d'une porte, alors on la verra si le joueur ouvre la porte dans la salle a droite de la porte).
* etc...

Si le clic est droit alors celle ouvre un petit popup avec des options.
* raccourcis pour le zoom in-game
* peut afficher les entités présentes sur la case
* mettre en évidence les pièces
* mettre en évidence les cases bloquantes

# Gomme

Gommer retire tous les rectangles (salle entière) si la gomme les intersecte. Si la salle contient des entités, alors elle sont toutes supprimés. (on pourrait créer une corbeille mais il y aurait undo et redo)

# Calques

On peut voir les niveaux et les afficher/cacher
Si on peut sélectionner son niveau actuel.
Tous les éléments ajoutés et retirés le seront sur ce niveaux.
Un clic sur une entité ouvrira la fenêtre sur l'entité a ce niveau.

# Corbeille

On pourra avoir une corbeille.
Idéalement on y trouverait les énigmes supprimés, les entités supprimées.

*inspiré de TILED*

![tiled](https://user-images.githubusercontent.com/54904135/70273533-8fa42580-17aa-11ea-99c6-ae587e644b2a.png)