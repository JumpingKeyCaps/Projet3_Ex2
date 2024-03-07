package com.openclassrooms.myrepo.data.service;

import com.openclassrooms.myrepo.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Cette classe simule la récupération de tâches depuis une API.
 * Les tâches générées sont utilisées à des fins de démonstration.
 */
public class TaskApi {
    /**
     * La constante qui nous permet de fixer la date d'expiration de la tache par défaut à 10 jours après la création de la tache.
     */
    final int DEFAULT_DATE_LIMITE = 10;
    /**
     * Récupère une liste de tâches simulées depuis l'API.
     *
     * @return Une liste de tâches simulées avec des descriptions pré-définies.
     */
    public List<Task> getTasks() {
        // Simule la récupération des tâches depuis une API
        List<Task> tasks = new ArrayList<>();
        // Pour chaque tache que l'on gênèrent une date limite via la méthode fakeTaskGenerator()
        //On crée des taches avec des temps limite personnalisée.
        tasks.add(new Task("Faire les courses pour le dîner",fakeDueTimeTaskGenerator(false,1)));
        tasks.add(new Task("Préparer le rapport pour la réunion",fakeDueTimeTaskGenerator(false,2)));
        tasks.add(new Task("Répondre aux e-mails en attente",fakeDueTimeTaskGenerator(false,3)));
        tasks.add(new Task("Faire de l'exercice pendant 30 minutes",fakeDueTimeTaskGenerator(false,5)));
        tasks.add(new Task("Planifier les vacances d'été",fakeDueTimeTaskGenerator(false,8)));
        //On crée des taches avec des temps limite par défaut.
        tasks.add(new Task("Rendre le livre à la bibliothèque",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Réviser pour l'examen de mathématiques",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Appeler le plombier pour la fuite d'eau",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Nettoyer le garage",fakeDueTimeTaskGenerator(true,null)));
        //On simule une erreur de paramètre en utilisant la méthode.
        tasks.add(new Task("Préparer une liste de courses",fakeDueTimeTaskGenerator(false,null)));
        return tasks;
    }

    /**
     * Générateur de date limite pour simuler un comportement par défaut ou personnalisée
     *
     * @param isDefaultDueTime  Permet de déterminer si on applique une date limite par défaut a la tache (true) ou une date custom (false).
     * @param dueTimeInDays Permet dans le cas où isDefaultDueTime est false, de fixer une date limite en nombre de jours.
     *                      (On utilise une Integer plutôt qu'un int, car Integer peut être null)
     * @return Un objet Date  représentant notre date limite pour la tache
     */
    private Date fakeDueTimeTaskGenerator(boolean isDefaultDueTime, Integer dueTimeInDays){
        //On récupère une instance de Calendar.
        Calendar calendar = Calendar.getInstance();
        //On reset notre calendar
        calendar.clear();
        //On lui défini la date actuelle (on simule une tache qui a été créée aujourd'hui.)
        calendar.setTime(new Date());
        //discrimination du mode default vs custom
        if(isDefaultDueTime){
            // Valeur par défaut (10 jours))
            calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        }else{
            // Valeur personnalisée (On sécurise avec un block Try{}catch en cas d'erreur d'utilisation de la méthode)
            try{
                calendar.add(Calendar.DAY_OF_YEAR, dueTimeInDays);
            }catch (NullPointerException e){
                //on fix la valeur par défaut
                calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
            }
        }
        return calendar.getTime();
    }

}
