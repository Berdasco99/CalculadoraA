package com.example.calculadoraa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.input).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0,cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        if(getString(R.string.input).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPosition + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
            display.setSelection(cursorPosition + 1);
        }

    }

    //Estos metodos son los botones de la calculadora, este apartado es el teclado numerico.
    public void cero(View view){
        updateText("0");
    }

    public void uno(View view){
        updateText("1");
    }

    public void dos(View view){
        updateText("2");
    }


    public void tres(View view){
        updateText("3");
    }

    public void cuatro(View view){
        updateText("4");
    }


    public void cinco(View view){
        updateText("5");
    }


    public void seis(View view){
        updateText("6");
    }


    public void siete(View view){
        updateText("7");

    }


    public void ocho(View view){
        updateText("8");
    }


    public void nueve(View view){
        updateText("9");
    }




//Estos son los botones que no corresponden al teclado numerico.


    public void coma(View view){
        updateText(",");
    }

    public void suma(View view){
        updateText("+");
    }

    public void resta(View view){
        updateText("-");
    }

    public void multiplicacion(View view){
        updateText("×");
    }

    public void division(View view){
        updateText("÷");
    }

    public void AC(View view){
        display.setText("");

    }

    //Este metodo determina si el ultimo caracter que hemos escrito es una ^, para que en ese caso nos sea imposible escribir dos seguidas ya que la calculadora daria error.
    public void potencia(View view){
        int cursorPos = display.getSelectionStart();
        String linea = display.getText().toString();
        int longitud = linea.length();
        int lastchar = longitud - 1;

        char coso = linea.charAt(lastchar);
        String coso2 = String.valueOf(coso).toString();
        if(!coso2.equals("^")){
            updateText("^");
        }else{
            Toast errorPotencia = Toast.makeText(this , "No puedes poner dos potencias seguidas", Toast.LENGTH_SHORT);
            errorPotencia.show();
        }

    }

    //String coso2 = String.valueOf(coso).toString();
    //
    //        display.setSelection(cursorPos+1);


    //Aqui van metodos que no son para actualizar el editText.

    public void resultado(View view){

    }


    //En este metodo se crean dos contadores para saber cuantos parentesis tenemos de cada tipo abiertos"(" o cerrados ")", con un bucle for contamos cuantos parentesis de cada hay en la frase
    //creamos dos metodos if en el caso del primero lo que hace esque si hay la misma cantidad de parentesis o si el ultimo caracter de la linea es un parentesis abierto "(" el programa escribira otro parentesis abierto "("
    //esto funciona de forma que podemos poner todos los parentesis abiertos seguidos que queramos en el caso de que vayamos a hacer una ecuacion que use mas de uno, el otro if funciona cuando dos condiciones se cumplen,
    //la cantidad de parentesis cerrados ")" es menor que la de abiertos "(" y el ultimo caracter de la linea es diferente a "(" esto hace que imprimamos parentesis de cierre hasta que el numero se iguale, por lo que si
    //por ejemplo escribimos "(((8+2" el botón imprimiría parentesis de cierre 3 veces quedando la linea asi "(((8+2)))".

    public void parentesis(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = display.getText().length();

        for(int i =0; i < cursorPos; i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar +=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar +=1;
            }
        }

        if(openPar == closedPar || display.getText().toString().substring(textLength-1,textLength).equals(("("))){
            updateText("(");
        }
        else if(closedPar < openPar && !display.getText().toString().substring(textLength-1,textLength).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);

    }

    //Lo que hace este metodo es checkear la posicion del cursor y la longitud del texto y en el caso de que el raton no este en la posición cero (lo cual implicaria que no hay mas numeros a su izquierda) y que
    //la linea de texto tenga al menos 1 caracter este se encarga de cambiar el caracter en la posicion directamente a la izquierda del cursor por un espacion en blanco (""), de ahi que en replace ponga cursorPos-1
    //luego simplemente coge el texto modificado y lo vuelve a poner en el edittext y tambien pone el cursor en la posicion en la que estaba.
    public void delete(View view){
        int cursorPos = display.getSelectionStart();
        int textLength = display.getText().length();

        if(cursorPos!=0 && textLength!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }











}