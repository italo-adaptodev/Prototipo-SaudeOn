package com.example.prototipo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototipo2.Modelos.Agenda;
import com.example.prototipo2.Modelos.Model;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Agenda> agenda;

    public MainAdapter(ArrayList<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {
        viewHolder.Nome_paciente.setText(agenda.get(i).getPaciente());
        viewHolder.motivo_paciente.setText(agenda.get(i).getMotivo());
        viewHolder.data_paciente.setText(agenda.get(i).getData());
        viewHolder.turno_paciente.setText(agenda.get(i).getTurno());
        viewHolder.
    }

    @Override
    public int getItemCount() {
        return agenda.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Nome_paciente;
        public TextView motivo_paciente;
        public TextView data_paciente;
        public TextView turno_paciente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Nome_paciente = itemView.findViewById(R.id.recy_paciente);
            motivo_paciente = itemView.findViewById(R.id.recy_motivo);
            data_paciente = itemView.findViewById(R.id.recy_data);
            turno_paciente = itemView.findViewById(R.id.recy_turno);


        }
    }
}
