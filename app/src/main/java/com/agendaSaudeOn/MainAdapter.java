package com.agendaSaudeOn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agendaSaudeOn.Modelos.Agenda;

import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Agenda> agenda;
    private Agenda agendaView;


    public MainAdapter(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        agendaView = agenda.get(i);
        viewHolder.Nome_paciente.setText(agendaView.getPaciente());
        viewHolder.motivo_paciente.setText(agendaView.getMotivo());
        viewHolder.turno_paciente.setText(agendaView.getTurno());
        viewHolder.endereco_paciente.setText(agendaView.getEndereco());

    }

    private void showEndereco(Context c, String agenda) {
        Uri gmmIntentUri = Uri.parse(Uri.encode(agenda));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(c.getPackageManager()) != null) {
            c.startActivity(mapIntent);
        }
    }

    @Override
    public int getItemCount() {
        return agenda.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Nome_paciente;
        TextView motivo_paciente;
        TextView turno_paciente;
        TextView endereco_paciente;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showEndereco(view.getContext(), agendaView.getEndereco());
                }
            });
            Nome_paciente = itemView.findViewById(R.id.recy_paciente);
            motivo_paciente = itemView.findViewById(R.id.recy_motivo);
            turno_paciente = itemView.findViewById(R.id.recy_turno);
            endereco_paciente = itemView.findViewById(R.id.recy_endereco);
        }


    }






}
