package br.com.alves.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.alves.agendaapp.dao.JogadorDAO;
import br.com.alves.agendaapp.entidade.Jogador;

public class ListaJogadoresActivity extends AppCompatActivity {

    private ListView listaJogadores;

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

        //Buscando view by id
        listaJogadores = findViewById(R.id.lista_alunos);
        registerForContextMenu(listaJogadores);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();

        //Buscando view by id
        listaJogadores = findViewById(R.id.lista_alunos);
    }

    private void carregarLista() {
        //Buscando dados do banco
        JogadorDAO jogadorDAO = new JogadorDAO(this);
        List<Jogador> jogadores = jogadorDAO.selectAll();
        jogadorDAO.close();

        //Binding dados
        ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, jogadores);
        listaJogadores.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem item = menu.add("Deletar");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Jogador jg = (Jogador) listaJogadores.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
                Toast.makeText(ListaJogadoresActivity.this, "O Jogador " + jg.getNome() + " foi excluído com sucesso.", Toast.LENGTH_LONG).show();
                JogadorDAO jogadorDAO = new JogadorDAO(ListaJogadoresActivity.this);
                jogadorDAO.excluirJogador(jg.getId());
                carregarLista();
                return false;
            }
        });
    }
}