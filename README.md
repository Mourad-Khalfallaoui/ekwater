Démarrez l'application soit à partir de l'IDE ou bien en lançcant la commande : 
mvn spring-boot:run

Une fois démarrée, l'application est testable directement sur le navigateur ou bien avec Postman en pointant sur l’URL suivante:

http://localhost:8080/tarif/{referenceClient}/{energyType}/{moi}/{année}
exemple : http://localhost:8080/tarif/EKW00000005/gaz/1/2024

Pour info, le mock que j'ai mis en place pour le test ne prend en compte que le mois de janvier (1) et l'année 2024 et les références client qui sont accepté doivent être dans l’intervalle [EKW00000000, EKW00000009] 
