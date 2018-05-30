package br.com.alves.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.alves.agendaapp.dao.JogadorDAO;
import br.com.alves.agendaapp.entidade.Jogador;

public class ListaJogadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Iniciando tela propriedades default
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Button btnnovo = findViewById(R.id.lista_btnnovo);
        btnnovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent form = new Intent(ListaJogadoresActivity.this, FormularioActivity.class);
                startActivity(form);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        //Buscando view by id
        ListView listaJogadores = findViewById(R.id.lista_alunos);

        //Buscando dados do banco
        JogadorDAO jogadorDAO = new JogadorDAO(this);
        List<Jogador> jogadores = jogadorDAO.selectAll();
        jogadorDAO.close();

        //Binding dados
        ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, jogadores);
        listaJogadores.setAdapter(adapter);
    }
}
