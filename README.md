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
  
# Utilisation : endpoint missing

### Obtenir la liste des absents par date 

    http://localhost:8080/missing/date/{exemple_de_date}

    http://localhost:8080/missing/date/2019-05-23

### Obtenir la liste des absents par ID d'étudiant

    http://localhost:8080/missing/id/{id}
    
    http://localhost:8080/missing/id/STD230003

### Obtenir la liste des absents par nom de cours

    http://localhost:8080/missing/name/{nom_de_la_matiere}

    http://localhost:8080/missing/name/PROG1

### Obtenir toute la liste des absents

    http://localhost:8080/missing/all

### Ajouter un nouvel enregistrement d'absence

    http://localhost:8080/missing/add

    format body :

    {
        "studentId": "STD230006",
        "date": "2019-05-23",
        "subjectId": "LV1" 
    }

### Mettre à jour le cours d'un étudiant

    http://localhost:8080/missing/change/course?studentId={existing_id}&oldCours={existing_course_to_change}&newCours={new_course_to_add}&date={date_where we_want_to_apply_change}
    
    http://localhost:8080/missing/change/course?studentId=STD230003&oldCours=LV1&newCours=SYS1&date=2019-05-23

### Mettre à jour la date d'absence d'un étudiant   

    http://localhost:8080/missing/change/date?studentId={existing_id}&oldDate={date_to_change}&newDate={date_to_apply}
    
    http://localhost:8080/missing/change/date?studentId=STD230003&oldDate=2019-05-23&newDate=2019-05-24

### Justifier une absence
    
    http://localhost:8080/missing/justify?studentId={student_id}&subject={subject_he_missed}&date={date_of_missed_subject}

    http://localhost:8080/missing/justify?studentId=STD230003&subject=LV1&date=2019-05-23_

### Supprimer un enregistrement d'absence

    http://localhost:8080/missing/del?subject_id={subject_to_delete}&student_id={student_to_delete}&date={date_to_apply_change}
    
    http://localhost:8080/missing/del?subject_id=SYS1&student_id=STD230003&date=2019-05-23