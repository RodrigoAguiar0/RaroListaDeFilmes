package com.example.raro;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Essa classe é responsável por salvar diversos logs do aplicativo em um arquivo .txt externo,
 * permitindo que auditoria possa ser feita no próprio dispositivo
 *
 * @author Rodrigo Aguiar
 * @since 11/06/2021
 */
public class LogRegister {

    /**
     * Contém lógica responsável por criar o arquivo de logs e atualizar ele conforme novas
     * informações são direcionadas.
     *
     * @param text: Mensagem para ser salva em logs
     * @param context: Contexto que os logs estão sendo criados
     * @param activity: Activity que chamou para salvar logs
     */
    public void appendLog(String text, Context context, Activity activity) {

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG,"Permission is granted");
            File logFile = new File(Environment.getExternalStorageDirectory(), "/DCIM/log.txt");
            if (!logFile.exists())
            {
                try
                {
                    logFile.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            try
            {
                BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                buf.append(text);
                buf.newLine();
                buf.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else{
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }



}
