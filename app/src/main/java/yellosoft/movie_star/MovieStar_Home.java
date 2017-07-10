package yellosoft.movie_star;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieStar_Home extends AppCompatActivity {

    Button bt;
    private Context context;
    List<Stars> starss = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_star__home);


        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, starss);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        starss.add(new Stars ("Аль Пачино", "Пол: Мужской", "Возвраст: 77 лет",R.drawable.al));
        starss.add(new Stars ("Ма́рлон Брандо", "Пол: Мужской", "Возвраст: 25 лет",R.drawable.bra));
        starss.add(new Stars ("Каан Джеймс", "Пол: Мужской", "Возвраст: 77 лет",R.drawable.dj));
        starss.add(new Stars ("Стефанелли Симонетта", "Пол: Женский", "Возвраст: 62 года",R.drawable.sim));
        starss.add(new Stars ("Кастеллано Ричард", "Пол: Мужской", "Возвраст: 55 лет",R.drawable.ka));
    }

    // Кнопа Quit
    public void QuitLis(View view) {
        String[] s = {"Выберите:", "Restart", "Quit"};
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(MovieStar_Home.this,
                android.R.layout.simple_spinner_item, s);

        bt = (Button) findViewById(R.id.button_send);
        final Spinner sp = new Spinner(MovieStar_Home.this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);
        AlertDialog.Builder builder = new AlertDialog.Builder(MovieStar_Home.this);
        builder.setView(sp);
        builder.create().show();
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Toast.makeText(MovieStar_Home.this, "Restart...", Toast.LENGTH_SHORT).show();
                    Intent mIntent = getIntent();
                    finish();
                    startActivity(mIntent);
                }
                if (position == 2) {
                    Toast.makeText(MovieStar_Home.this, "Closed...", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

}
