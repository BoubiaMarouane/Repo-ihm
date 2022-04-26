package edu.polytech.repo_ihm.datas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.polytech.repo_ihm.R;

public class QuantityAdapter extends RecyclerView.Adapter<QuantityAdapter.ViewHolder> {

    private ArrayList<Ingredients> ingredients;
    private ArrayList<Boolean> ingredientIsSelected;
    private ArrayList<String> quantityPerIngredient;

    public QuantityAdapter(ArrayList<Ingredients> ingredients, ArrayList<Boolean> ingredientIsSelected, ArrayList<String> quantityPerIngredient) {
        this.ingredients = ingredients;
        this.ingredientIsSelected = ingredientIsSelected;
        this.quantityPerIngredient = quantityPerIngredient;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quantity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = ingredients.get(position).french();
        String quantity = quantityPerIngredient.get(position);
        holder.name.setText(name);
        holder.quantity.setText(quantity);
        if (ingredientIsSelected.get(position)) {
            holder.name.setVisibility(View.VISIBLE);
            holder.quantity.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.qtt_ingredient);
            quantity = itemView.findViewById(R.id.qtt_value);
        }
    }
}
