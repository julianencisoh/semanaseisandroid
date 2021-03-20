package com.example.androidsemanaseis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button botonarriba;
    private Button botonderecha;
    private Button botonizquierda;
    private Button botonabajo;
    private Button botoncolor;
    private BufferedWriter bwriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonarriba = findViewById(R.id.botonarriba);
        botonderecha = findViewById(R.id.botonderecha);
        botonizquierda = findViewById(R.id.botonizquierda);
        botonabajo = findViewById(R.id.botonabajo);
        botoncolor = findViewById(R.id.botoncolor);


        botonarriba.setOnClickListener(
                v -> {
                    enviarAEclipse(1);
                }
        );

        botonabajo.setOnClickListener(
                v -> {
                    enviarAEclipse(2);


                }
        );

        botonderecha.setOnClickListener(
                v -> {
                    enviarAEclipse(3);
                }
        );

        botonizquierda.setOnClickListener(
                v -> {
                    enviarAEclipse(4);
                }
        );


        botoncolor.setOnClickListener(
                v -> {
                    enviarAEclipse(5);
                }
        );

        new Thread(
                () -> {
                    try {
                        Socket socket = new Socket("10.0.2.2",5000);
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
        ).start();



    }

    private void enviarAEclipse(int i) {

        new Thread(
                () -> {

             Gson gson = new Gson();
             Bola bolita  = new Bola(i);
             String movimiento = gson.toJson(bolita);

                    try {
                        bwriter.write(movimiento + "\n");
                        bwriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();

    }
}