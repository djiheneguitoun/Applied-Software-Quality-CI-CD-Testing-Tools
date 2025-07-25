Feature: Calcul du déterminant d'une matrice 3x3

  Scenario: Calculer le déterminant d'une matrice
    Given une matrice de taille
      | 1 | 2 | 3 |
      | 4 | 5 | 6 |
      | 7 | 8 | 9 |
    When je calcule le déterminant de la matrice
    Then le résultat devrait être égal à 0
