package com.agendaSaudeOn.AgendaProfissionalPackage;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agendaSaudeOn.R;

import java.util.List;

public class ProfissionalAdapter extends RecyclerView.Adapter<ProfissionalAdapter.ViewHolder> {

    private List<ProntuarioProfissional> prontuarioProfissionals;
    private ProntuarioProfissional prontuarioProfissionalView;


    public ProfissionalAdapter(List<ProntuarioProfissional> prontuarioProfissionals) {
        this.prontuarioProfissionals = prontuarioProfissionals;
    }

    @NonNull
    @Override
    public ProfissionalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        prontuarioProfissionalView = prontuarioProfissionals.get(i);
        viewHolder.Nome_paciente.setText(prontuarioProfissionalView.getPaciente());
        viewHolder.motivo_paciente.setText(prontuarioProfissionalView.getMotivo());
        viewHolder.turno_paciente.setText(prontuarioProfissionalView.getTurno());
        viewHolder.endereco_paciente.setText(prontuarioProfissionalView.getEndereco());

        showEndereco(viewHolder, prontuarioProfissionalView);

    }

    private void showEndereco(final ViewHolder v, final ProntuarioProfissional prontuario) {
        v.openMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.com/maps?q=" + prontuario.getEndereco();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return prontuarioProfissionals.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Nome_paciente;
        TextView motivo_paciente;
        TextView turno_paciente;
        TextView endereco_paciente;
        ImageButton openMaps;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            openMaps = itemView.findViewById(R.id.btn_maps);
            Nome_paciente = itemView.findViewById(R.id.recy_paciente);
            motivo_paciente = itemView.findViewById(R.id.recy_motivo);
            turno_paciente = itemView.findViewById(R.id.recy_turno);
            endereco_paciente = itemView.findViewById(R.id.recy_endereco);
        }


    }






}
