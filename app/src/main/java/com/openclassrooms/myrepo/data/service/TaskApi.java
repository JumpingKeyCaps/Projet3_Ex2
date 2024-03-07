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
     * La constante qui nous permet de fixer la date d'expiration de la tache par default a 10 jours apres la creation de la tache.
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
        // Pour chaque task que l'on on genere une date limite via la methode fakeTaskGenerator
        //on genere des taches avec des temps limite personaliser
        tasks.add(new Task("Faire les courses pour le dîner",fakeDueTimeTaskGenerator(false,1)));
        tasks.add(new Task("Préparer le rapport pour la réunion",fakeDueTimeTaskGenerator(false,2)));
        tasks.add(new Task("Répondre aux e-mails en attente",fakeDueTimeTaskGenerator(false,3)));
        tasks.add(new Task("Faire de l'exercice pendant 30 minutes",fakeDueTimeTaskGenerator(false,5)));
        tasks.add(new Task("Planifier les vacances d'été",fakeDueTimeTaskGenerator(false,8)));

        //on genere des taches avec des temps limite  par default
        tasks.add(new Task("Rendre le livre à la bibliothèque",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Réviser pour l'examen de mathématiques",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Appeler le plombier pour la fuite d'eau",fakeDueTimeTaskGenerator(true,null)));
        tasks.add(new Task("Nettoyer le garage",fakeDueTimeTaskGenerator(true,null)));

        //on simule une erreur de parametre en utilisant la methode
        tasks.add(new Task("Préparer une liste de courses",fakeDueTimeTaskGenerator(false,null)));
        return tasks;
    }

    /**
     * Generateur de date limite pour simuler un comportement par default ou personaliser
     *
     * @param isDefaultDueTime  permet de determiner si on applique une date limite par default a la tache (true) ou une date custom (false).
     * @param dueTimeInDays permet dans le cas ou isDefaultDueTime est false, de fixer une date limite en nombre de jour.
     *                      (on utilise une Integer plutot q'un int car Integer peut etre null)
     * @return un objet Date  representant notre date limite pour la tache
     */
    private Date fakeDueTimeTaskGenerator(boolean isDefaultDueTime, Integer dueTimeInDays){
        //on recupere une instance de Calendar
        Calendar calendar = Calendar.getInstance();
        //on reset notre calendar
        calendar.clear();
        //on lui definie la date actuel (on simule une tache qui a ete creer aujourdhui)
        calendar.setTime(new Date());
        //discrimination du mode default vs custom
        if(isDefaultDueTime){
            // valeur par default (10 jours))
            calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        }else{
            // valeur personaliser (on securise avec un block Try{}catch en cas d'erreur d'utilisation de la methode)
            try{
                calendar.add(Calendar.DAY_OF_YEAR, dueTimeInDays);
            }catch (NullPointerException e){
                //on fix la valeur par default
                calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
            }
        }
        return calendar.getTime();
    }

}
