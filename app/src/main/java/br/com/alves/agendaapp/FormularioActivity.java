package br.com.alves.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.alves.agendaapp.dao.JogadorDAO;
import br.com.alves.agendaapp.entidade.Jogador;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_salvar:

                //Conexão com o banco
                JogadorDAO jogadorDAO = new JogadorDAO(this);

                //Busca jogador da tels
                Jogador jogador = retonaAluno();


                //Insere aluno no banco
                jogadorDAO.insert(jogador);
                jogadorDAO.close();
                Toast.makeText(FormularioActivity.this, "Aluno: " + jogador.getNome() + " salvo com sucesso", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Jogador retonaAluno() {

        Jogador jogador = new Jogador();

        EditText campoNome = findViewById(R.id.form_nome);
        jogador.setNome(campoNome.getText().toString());

        EditText campoClube = findViewById(R.id.form_clube);
        jogador.setClube(campoClube.getText().toString());

        EditText campoIdade = findViewById(R.id.form_idade);
        jogador.setIdade(Integer.parseInt(campoIdade.getText().toString()));

        RatingBar campoNota = findViewById(R.id.form_nota);
        jogador.setNota(Double.valueOf(campoNota.getProgress()));

        return jogador;
    }
}
