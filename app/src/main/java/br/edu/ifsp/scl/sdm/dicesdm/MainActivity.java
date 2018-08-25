package br.edu.ifsp.scl.sdm.dicesdm;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Gerador de numeros randomicos usado para simular o lancamento do dado
    private Random geradorRandomico;

    // Referencia para componentes visuais no arquivo de layout
    private TextView resultadoTextView;
    private ImageView resultadoImageVew;
    private ImageView resultado2ImageView;
    private Spinner numDadosSpinner;
    private EditText numFacesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciando gerador randomico
        geradorRandomico = new Random(System.currentTimeMillis());

        // Recuperando referencia para componentes de Layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
        resultadoImageVew = findViewById(R.id.resultadoImageView);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);
        numDadosSpinner = findViewById(R.id.numDadosSpiner);
        numFacesEditText = findViewById(R.id.numFacesEditText);
    }

    public void jogarDado(View view) {
        if (view.getId() == R.id.jogarDadoButton) {

            int numDices = Integer.parseInt(numDadosSpinner.getSelectedItem().toString());
            String numFacesString = numFacesEditText.getText().toString();
            int numFaces = Integer.parseInt(numFacesEditText.getText().toString());

            if (numFaces > 0) {
                if (numFaces > 6) {
                    resultadoImageVew.setVisibility(View.GONE);
                    resultado2ImageView.setVisibility(View.GONE);
                } else {
                    resultadoImageVew.setVisibility(View.VISIBLE);

                    if (numDices == 2) {
                        resultado2ImageView.setVisibility(View.VISIBLE);
                    } else {
                        resultado2ImageView.setVisibility(View.GONE);
                    }
                }

                String resultadoText = "";

                for (int i = 1; i <= numDices; i++) {
                    int resultado = geradorRandomico.nextInt(numFaces) + 1;

                    resultadoText += resultado + " ";

                    ImageView imageView = i == 1? resultadoImageVew : resultado2ImageView;

                    switch (resultado) {
                        case 1:
                            imageView.setImageResource(R.drawable.dice_1);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.dice_2);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.dice_3);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.dice_4);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.dice_5);
                            break;
                        case 6:
                            imageView.setImageResource(R.drawable.dice_6);
                            break;
                        default:
                            break;
                    }
                }

                resultadoTextView.setText(getString(R.string.face_sorteada) + resultadoText);
            } else {
                Toast.makeText(this, "Número de faces deve ser maior que 0!", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
