package tekhne.com.practicafragments;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import tekhne.com.practicafragments.interfaces.ItemListener;

public class MainActivity extends AppCompatActivity implements ItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            PeliculaFragment firstFragment = new PeliculaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, firstFragment);
            ft.commit();
        }
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            DetalleFragment segundoFragment = new DetalleFragment();
            Bundle parametro = new Bundle();
            parametro.putInt("posicion", 0);
            segundoFragment.setArguments(parametro);
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            ft2.add(R.id.flContainer2, segundoFragment);
            ft2.commit();
        }
    }

    @Override
    public void onItemSelect(int position) {
        Toast.makeText(this, " Fragment A: posicion - "+ position, Toast.LENGTH_SHORT).show();

        // Load Pizza Detail Fragment
        DetalleFragment secondFragment = new DetalleFragment();

        Bundle args = new Bundle();
        args.putInt("posicion", position);
        secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment) // replace flContainer
                    //.addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, secondFragment) // replace flContainer
                    .addToBackStack(null)
                    .commit();
        }
    }
}
