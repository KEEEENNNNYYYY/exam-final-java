# exam final java : gestion d'absence

# remarque :

    - Dans certain, les endpoints retournent des erreurs si les conditions ne sont pas remplie,
    comme dans le cas des ajouts d'absences si l'eleve ou la matiere n'existe pas encore, d'où la necessité 
    de la creation de ces autres champs en premiers lieu

    - La deletion ne sera pas disponible dans aucun cas, à la place il y aura les state,qui ce dernier montrera
    si une matiere est toujours en cours d'application, ou si un eleve fait encore parti de l'ecole en question

    - Pas d'insert pour la table missing_list car l'insert devrait se faire via la method POST 
    due au fonction à l'interieur du DAO (petit spoil,chaque ajout dans missing_list augmentera 
    le compteur de unjustified missing,la justification de l'absence reduira ce compteur,
    cela fait parti du role de la fonction add et justify,donc un insert direct psotgres va créer beaucoup de confusion)

# creation de la base donnée : 

    -- Creation de la table student:
  

    CREATE TABLE student (
        id character varying NOT NULL,
        first_name character varying,
        last_name character varying,
        birthday timestamp without time zone,
        grades character varying,
        address character varying,
        sexe character varying,
        cored boolean,
        email character varying,
        groupe character varying,
        unjustified_missing_count integer DEFAULT 0,
        state character varying DEFAULT 'IN'::character varying,
        PRIMARY KEY (id)
    );


    -- Creation de la table subject


    CREATE TABLE subject (
        name character varying NOT NULL,
        total_hours integer,
        teacher character varying,
        state character varying(255),
        PRIMARY KEY (name)
    );


    -- Creation de la table the missing_list 


    CREATE TABLE missing_list (
        student_id character varying,
        date timestamp without time zone NOT NULL,
        subject_id character varying,
        first_name character varying,
        last_name character varying,
        justified character varying,
        FOREIGN KEY (student_id) REFERENCES student (id),
        FOREIGN KEY (subject_id) REFERENCES subject (name) ON UPDATE CASCADE
    );

# exemple d'insert :

    -- Pour student : 

    INSERT INTO student (id, first_name, last_name, birthday, grades, adress, sexe, cored, email, groupe, unjustified_missing_count, state) VALUES
    ('STD23001', 'Alice', 'Dupont', '2005-05-15', 'L1', '10 rue de Paris', 'F', false, 'alice.dupont@example.com', 'J1', 0, 'IN'),
    ('STD23002', 'Bob', 'Martin', '2005-06-20', 'L1', '12 avenue de Lyon', 'M', false, 'bob.martin@example.com', 'J1', 0, 'IN'),
    ('STD23003', 'Chloé', 'Bernard', '2005-07-12', 'L1', '5 boulevard de Marseille', 'F', false, 'chloe.bernard@example.com', 'J2', 0, 'IN'),
    ('STD23004', 'David', 'Leroy', '2005-08-25', 'L1', '15 rue de Lille', 'M', false, 'david.leroy@example.com', 'J2', 0, 'IN'),
    ('STD23005', 'Émilie', 'Lemoine', '2005-09-10', 'L1', '20 avenue de Bordeaux', 'F', false, 'emilie.lemoine@example.com', 'J1', 0, 'IN'),
    ('STD23006', 'François', 'Petit', '2005-10-05', 'L1', '30 rue de Nice', 'M', false, 'francois.petit@example.com', 'J1', 0, 'IN'),
    ('STD23007', 'Géraldine', 'Moreau', '2005-11-18', 'L1', '25 rue de Nantes', 'F', false, 'geraldine.moreau@example.com', 'J2', 0, 'IN'),
    ('STD23008', 'Hugo', 'Girard', '2005-12-30', 'L1', '40 avenue de Strasbourg', 'M', false, 'hugo.girard@example.com', 'J2', 0, 'IN'),
    ('STD23009', 'Isabelle', 'Rousseau', '2006-01-14', 'L1', '50 boulevard de Toulouse', 'F', false, 'isabelle.rousseau@example.com', 'J1', 0, 'IN'),
    ('STD23010', 'Julien', 'Dufour', '2006-02-22', 'L1', '60 rue de Montpellier', 'M', false, 'julien.dufour@example.com', 'J1', 0, 'IN'),
    ('STD23011', 'Karine', 'Lambert', '2006-03-03', 'L1', '70 avenue de Lille', 'F', false, 'karine.lambert@example.com', 'J2', 0, 'IN'),
    ('STD23012', 'Lucas', 'Pichon', '2006-04-11', 'L1', '80 boulevard de Rennes', 'M', false, 'lucas.pichon@example.com', 'J2', 0, 'IN'),
    ('STD23013', 'Marie', 'Garnier', '2006-05-19', 'L1', '90 rue de Grenoble', 'F', false, 'marie.garnier@example.com', 'J1', 0, 'IN'),
    ('STD23014', 'Nicolas', 'Bonnet', '2006-06-30', 'L1', '100 avenue de Reims', 'M', false, 'nicolas.bonnet@example.com', 'J1', 0, 'IN'),
    ('STD23015', 'Océane', 'Brun', '2006-07-22', 'L1', '110 rue de Tours', 'F', false, 'oceane.brun@example.com', 'J2', 0, 'IN');



    -- Pour subject : 

    INSERT INTO public.subject (name, total_hours, teacher, state) VALUES
    ('PROG1', 40, 'Smith', 'OnGoing'),
    ('SYS1', 35, 'Johnson', 'OnGoing'),
    ('LV1', 50, 'Brown', 'OnGoing'),
    ('MGT1', 45, 'White', 'OnGoing'),
    ('THEORIE1', 30, 'Green', 'OnGoing'),
    ('PROG2', 40, 'Black', 'OnGoing'),
    ('WEB1', 50, 'Gray', 'OnGoing'),
    ('WEB2', 25, 'Blue', 'OnGoing'),
    ('SYS2', 40, 'Purple', 'OnGoing'),
    ('LV2', 20, 'Red', 'OnGoing');


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

# utilisation : endpoint subject :

### Obtenir tous les cours :

    http://localhost:8080/subject/all

### Ajouter un nouveau cours 

    http://localhost:8080/subject/add

    body format :

    {
        "name": "NEW_SUBJECT",
        "totalHours": 40,
        "teacher": "Dr New"
    }

### Rechercher un cours par nom

    http://localhost:8080/subject/search?name={cours_à_rechercher}


    http://localhost:8080/subject/search?name=MGT1

### Obtenir les cours d'un enseignant

    http://localhost:8080/subject/show/{teacher}

    
    http://localhost:8080/subject/show/Doe

### Mettre à jour l'état d'un cours :

    http://localhost:8080/subject/update/state?id={nom_de_la_matiere}&value={nouvelle_etat_selon_l_enum}

    http://localhost:8080/subject/update/state?id=MGT1&value=Out

### Mettre à jour le nom d'un cours

    http://localhost:8080/subject/update/name?id={nom_de_la_matiere}&value={nouvelle_etat_selon_l_enum}

    http://localhost:8080/subject/update/state?id=MGT1&value=SYS1

### Mettre à jour le nombre total d'heures d'un cours

    http://localhost:8080/subject/update/name?id={nom_de_la_matiere}&value={nouvelle_valeur}

    http://localhost:8080/subject/update/state?id=MGT1&value=99

### Mettre à jour l'enseignant d'un cours

    http://localhost:8080/subject/update/name?id={nom_de_la_matiere}&value={nouvelle_valeur}

    http://localhost:8080/subject/update/state?id=MGT1&value=BenjaFolo
