package edu.polytech.repo_ihm.activities.ideeRecettes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.polytech.repo_ihm.R;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;
    private RecyclerViewClickListener listener;

    public IngredientAdapter(ArrayList<Ingredient> ingredients, RecyclerViewClickListener listener) {
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = ingredients.get(position).getFrench();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CheckedTextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (CheckedTextView) itemView.findViewById(R.id.ingredient_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ConstraintLayout v = (ConstraintLayout) view;
            CheckedTextView textView =(CheckedTextView) v.getChildAt(0);
            textView.toggle();
            System.out.println(textView.isChecked());
            listener.onClick(view, getBindingAdapterPosition());
        }
    }
}
