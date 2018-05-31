package br.com.alves.agendaapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.alves.agendaapp.entidade.Jogador;

public class JogadorDAO extends SQLiteOpenHelper {


    //Construtor
    public JogadorDAO(Context context) {

        super(context, "SQJogador", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Criar tabela
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE Jogadores (");
        sql.append("NUMG_Id INTEGER PRIMARY KEY,");
        sql.append("DESC_Nome VARCHAR(20) NOT NULL,");
        sql.append("DESC_Clube VARCHAR(20),");
        sql.append("NUMR_Idade INT,");
        sql.append("NUMR_Nota FLOAT");
        sql.append(");");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Jogador jogador) {

        //Pega instancia do banco
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("DESC_Nome", jogador.getNome());
        values.put("DESC_Clube", jogador.getClube());
        values.put("NUMR_Idade", jogador.getIdade());
        values.put("NUMR_Nota", jogador.getNota());

        db.insert("Jogadores", null, values);
    }

    public List<Jogador> selectAll() {

        //Query
        String sql = "SELECT * FROM Jogadores";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        //Populando lista de jogadores
        List<Jogador> listaRetorno = new ArrayList<>();
        while (cursor.moveToNext()) {

            Jogador jg = new Jogador();

            jg.setId(cursor.getInt(0));
            jg.setNome(cursor.getString(1));
            jg.setClube(cursor.getString(2));
            jg.setIdade(cursor.getInt(3));
            jg.setNota(cursor.getDouble(4));

            listaRetorno.add(jg);
        }

        cursor.close();
        return listaRetorno;
    }

    public void excluirJogador(Integer id) {

        //Criar tabela
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM Jogadores ");
        sql.append("WHERE NUMG_Id = ");
        sql.append(id);

        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(sql.toString());
    }
}
