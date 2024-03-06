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

        //on recupere une instance de Calendar
        Calendar calendar = Calendar.getInstance();
        //on lui definie la date actuel
        calendar.setTime(new Date());
        // Pour chaque task que l'on cree, on ajoute le nombre de jour par default a notre objet calendar et on passe la date retourner via .getTime() en parametre au constructor de la task a creer.
        tasks.add(new Task("Faire les courses pour le dîner",calendar.getTime()));

        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Préparer le rapport pour la réunion",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Répondre aux e-mails en attente",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Faire de l'exercice pendant 30 minutes",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Planifier les vacances d'été",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Rendre le livre à la bibliothèque",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Réviser pour l'examen de mathématiques",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Appeler le plombier pour la fuite d'eau",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Nettoyer le garage",calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, DEFAULT_DATE_LIMITE);
        tasks.add(new Task("Préparer une liste de courses",calendar.getTime()));
        return tasks;
    }
}
