package com.example.prototipo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototipo2.Modelos.Agenda;

import java.util.ArrayList;
import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Agenda> agenda = new ArrayList<>();

    public MainAdapter(ArrayList<Agenda> agenda) {
        this.agenda = agenda;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {

        Agenda agenda2 = agenda.get(i);

        viewHolder.Nome_paciente.setText(agenda2.getPaciente());
        viewHolder.motivo_paciente.setText(agenda2.getMotivo());
        viewHolder.data_paciente.setText(agenda2.getData());
        viewHolder.turno_paciente.setText(agenda2.getTurno());

    }

    @Override
    public int getItemCount() {
        return agenda.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Nome_paciente;
        TextView motivo_paciente;
        TextView data_paciente;
        TextView turno_paciente;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            Nome_paciente = itemView.findViewById(R.id.recy_paciente);
            motivo_paciente = itemView.findViewById(R.id.recy_motivo);
            data_paciente = itemView.findViewById(R.id.recy_data);
            turno_paciente = itemView.findViewById(R.id.recy_turno);



        }
    }
}
