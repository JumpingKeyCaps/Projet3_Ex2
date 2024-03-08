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
    private static final int DEFAULT_DATE_LIMITE = 10;
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
        tasks.add(new Task("Faire les courses pour le dîner",fakeDueTimeTaskGenerator(1)));
        tasks.add(new Task("Préparer le rapport pour la réunion",fakeDueTimeTaskGenerator(2)));
        tasks.add(new Task("Répondre aux e-mails en attente",fakeDueTimeTaskGenerator(3)));
        tasks.add(new Task("Faire de l'exercice pendant 30 minutes",fakeDueTimeTaskGenerator(5)));
        tasks.add(new Task("Planifier les vacances d'été",fakeDueTimeTaskGenerator(8)));
        //On crée des taches avec des temps limite par défaut.
        tasks.add(new Task("Rendre le livre à la bibliothèque",fakeDueTimeTaskGenerator(DEFAULT_DATE_LIMITE)));
        tasks.add(new Task("Réviser pour l'examen de mathématiques",fakeDueTimeTaskGenerator(DEFAULT_DATE_LIMITE)));
        tasks.add(new Task("Appeler le plombier pour la fuite d'eau",fakeDueTimeTaskGenerator(DEFAULT_DATE_LIMITE)));
        tasks.add(new Task("Nettoyer le garage",fakeDueTimeTaskGenerator(DEFAULT_DATE_LIMITE)));
        tasks.add(new Task("Préparer une liste de courses",fakeDueTimeTaskGenerator(DEFAULT_DATE_LIMITE)));
        return tasks;
    }

    /**
     * Générateur de date limite pour une tache
     *
     * @param dueTimeInDays Permet de fixer une date limite en nombre de jours.
     *
     * @return Un objet Date  représentant notre date limite pour la tache
     */
    private Date fakeDueTimeTaskGenerator(Integer dueTimeInDays){
        //On récupère une instance de Calendar.
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, dueTimeInDays);
        return calendar.getTime();
    }

}
