# exam final java : gestion d'absence

# Utilisation : endpoint student:

### liste de tout les etudiants :

    http://localhost:8080/students/all 

### liste de tout les etudiants en COR ou pas 
    
    http://localhost:8080/students/search/cored/false

    http://localhost:8080/students/search/cored/true

### liste de tout les etudiants par groupes 

    http://localhost:8080/students/search/groupe/{exemple}

    http://localhost:8080/students/search/groupe/J1

    http://localhost:8080/students/search/groupe/J2

### afficher les etudiants par année universitaire :

    http://localhost:8080/students/search/grades/{grades} 

    http://localhost:8080/students/search/grades/L1

    http://localhost:8080/students/search/grades/L2

### Afficher les étudiants par nombre d'absences injustifiées

    http://localhost:8080/students/search/missingCount?missingCount={count}

    http://localhost:8080/students/search/missingCount?missingCount=1

### Afficher un étudiant par ID

    http://localhost:8080/students/STD23xxx

    http://localhost:8080/students/STD23003

### Ajouter un étudiant

    http://localhost:8080/students/add

    format body :

    {
        "id": "STD230011",
        "firstName": "Alice",
        "lastName": "Dubois",
        "birthday": "2001-06-15",
        "grade": "L2",  
        "email": "alice.dubois@example.com",
        "adress": "10 rue de l'Université",
        "sexe": "F",  
        "cored": false,
        "groupe": "H2"  
    }

### Mettre à jour l'état d'un étudiant

    http://localhost:8080/students/STD230002/state?value={valeur_dans_model_enum_state}

    - state decris l'état d'un etudiant,si il a quitté l'etablissement ou non,
    -  si oui : out
    - sinon : in
    - Deja mentionné dans : package hei.school.kenny.attendance.model;
  
### Mettre à jour les informations d'un étudiant
    
    http://localhost:8080/students/change?id={STDxxxx}&{column_name}={newValue}

    valeur disponible pour : {column_name}
    - firstName
    - lastName
    - newEmail
    - newGrades
    - newGroupes
    - newBirthday