package com.openclassrooms.myrepo.ui;
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
//
    /**
     * ViewHolder pour afficher les éléments de la liste de tâches.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView factTextView;
        private final TextView dueTimeTextView;
        private final LinearProgressIndicator dueTimeProgressIndicator;

        /**
         * Constructeur du ViewHolder.
         *
         * @param itemView la View racine du layout inflater utiliser pour representer une ligne du RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // on recupere nos widgets via notre View racines passer en parametre du constructeur (itemView) dans laquelle a ete inflater notre layout task_item.xml
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
            //on formate la date limite via une methode dedier pour l'afficher dans son textView
            dueTimeTextView.setText(formatDueTimeToShow(task.getDueTime()));
            //on set la valeur de temps restant de notre ProgressIndicator via la methode de calcule dedier.
            dueTimeProgressIndicator.setProgress(calculateDueTimeProgress(task.getDueTime()));
        }

        /**
         * Formate notre temps limite en jours/mois/annees pour etre directement utiliser par son textview associer.
         *
         * @param taskDueTime La date limite de la tache a formater.
         * @return une String  representant la date limite dans un format dd/MM/yyyy  preceder d'un text informatif static
         */
        public String formatDueTimeToShow(Date taskDueTime){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return "Date limite : "+dateFormat.format(taskDueTime);
        }

        /**
         * Calcule la valeur de progression (en %) de la barre de progression de la date limite.
         *
         * @param dueTime La date limite de la tâche.
         * @return La progression actuel en pourcentage.
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
