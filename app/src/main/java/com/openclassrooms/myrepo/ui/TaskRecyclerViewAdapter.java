package com.openclassrooms.myrepo.ui;
import static android.provider.Settings.System.getString;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.openclassrooms.myrepo.R;
import com.openclassrooms.myrepo.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Un adaptateur pour afficher la liste de tâches dans un RecyclerView.
 */
public class TaskRecyclerViewAdapter extends ListAdapter<Task, TaskRecyclerViewAdapter.ViewHolder> {

    /**
     * Constructeur de l'adaptateur.
     */
    public TaskRecyclerViewAdapter() {
        super(new ItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     * ViewHolder pour afficher les éléments de la liste de tâches.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        final static String DATE_FORMAT_USED_PATTERN = "dd/MM/yyyy";
        private final TextView factTextView;
        private final TextView dueTimeTextView;
        private final LinearProgressIndicator dueTimeProgressIndicator;



        /**
         * Constructeur du ViewHolder.
         *
         * @param itemView La View racine du layout inflater utiliser pour représenter une ligne du RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // On récupère nos widgets via notre View racines passer en paramètre du constructeur (itemView) dans laquelle a été inflater notre layout task_item.xml
            factTextView = itemView.findViewById(R.id.task_description);
            dueTimeTextView = itemView.findViewById(R.id.task_duetime);
            dueTimeProgressIndicator = itemView.findViewById(R.id.task_duetime_progress_horizontal);
        }

        /**
         * Lie les données de la tâche au ViewHolder.
         *
         * @param task La tâche à afficher.
         */
        public void bind(Task task) {
            factTextView.setText(task.getDescription());
            //On formate la date limite via une méthode dédiée pour l'afficher dans son TextView.
            dueTimeTextView.setText(formatDueTimeToShow(task.getDueTime()));
            //On set la valeur de temps restant de notre ProgressIndicator via la méthode de calcul dédier.
            dueTimeProgressIndicator.setProgress(calculateDueTimeProgress(task.getDueTime()));
        }

        /**
         * Formate notre temps limite en jours/mois/annees pour être directement utilisé par son TextView associé.
         *
         * @param taskDueTime La date limite de la tache a formater.
         * @return Une String  représentant la date limite dans un format dd/MM/yyyy  précéder d'un texte informatif statique
         */
        private String formatDueTimeToShow(Date taskDueTime){
            final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_USED_PATTERN, Locale.getDefault());
            //on recupere le contexe via l'itemview, pour recuperer notre String resource,
            // puis on utilise le binding d'argument sur cette derniere pour y inclure notre notre date
            // (bonne pratique + permet de gerer la régionalisation)
            return itemView.getContext().getString(R.string.duetime_formated_text,dateFormat.format(taskDueTime));
        }

        /**
         * Calcule la valeur de progression (en %) de la barre de progression de la date limite.
         *
         * @param dueTime La date limite de la tâche.
         * @return La progression actuelle  en pourcentage.
         */
        private int calculateDueTimeProgress(Date dueTime) {
            //La date actuel
            Calendar currentDayCalendar = Calendar.getInstance();
            currentDayCalendar.set(Calendar.HOUR_OF_DAY, 0);
            currentDayCalendar.set(Calendar.MINUTE, 0);
            currentDayCalendar.set(Calendar.SECOND, 0);
            currentDayCalendar.set(Calendar.MILLISECOND, 0);

            // La date limite de la task
            Calendar DueTimeCalendar = Calendar.getInstance();
            DueTimeCalendar.setTime(dueTime);
            DueTimeCalendar.set(Calendar.HOUR_OF_DAY, 0);
            DueTimeCalendar.set(Calendar.MINUTE, 0);
            DueTimeCalendar.set(Calendar.SECOND, 0);
            DueTimeCalendar.set(Calendar.MILLISECOND, 0);

            //Calcule de la difference entre la date limite et la date actuel
            int daysRemaining = Math.toIntExact((DueTimeCalendar.getTimeInMillis() - currentDayCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000));
            //on retunr
            return 100 - (daysRemaining * 10);
        }
    }

    /**
     * Callback pour la comparaison des éléments de la liste.
     */
    private static class ItemCallback extends DiffUtil.ItemCallback<Task> {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getDescription().equals(newItem.getDescription());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    }
}
