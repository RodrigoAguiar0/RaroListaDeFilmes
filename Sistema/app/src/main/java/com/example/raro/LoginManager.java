package com.example.raro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Essa classe iria funcionar como a classe de login, que cuida do login do usuário quando entra
 * na plataforma. A ideia é que registrassemos no banco os usuários e eles pudessem entrar no
 * aplicativo, mas ficamos sem tempo.
 *
 * @author Rodrigo Aguiar
 * @since 11/06/2021
 */
public class LoginManager extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "RaroLogin.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "usuarios";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";


    public LoginManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] id = {COLUMN_ID};
        String [] user = {COLUMN_EMAIL};
        String [] userPassword = {COLUMN_PASSWORD};

        return true;
    }
}
