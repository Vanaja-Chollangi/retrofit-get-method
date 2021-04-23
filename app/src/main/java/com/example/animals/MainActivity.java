package com.example.animals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.animals.model.Animals;
import com.example.animals.network.APIclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        Call<List<Animals>>  call = APIclient.apIinterface().getAnimals();
        call.enqueue(new Callback<List<Animals>>() {
            @Override
            public void onResponse(Call<List<Animals>> call, Response<List<Animals>> response) {
                 if(response.isSuccessful()){
                     customAdapter = new CustomAdapter(response.body() , MainActivity.this);
                     gridView.setAdapter(customAdapter);


                 }else {
                     Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_SHORT).show();

                 }
                
            }

            @Override
            public void onFailure(Call<List<Animals>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occured"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public class CustomAdapter extends BaseAdapter{

        public List<Animals> animalsList;
        public Context context;

        public CustomAdapter(List<Animals> animalsList, Context context) {
            this.animalsList = animalsList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return animalsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.row_data , null);

            TextView name = view.findViewById(R.id.textview);
            ImageView imageView = view.findViewById(R.id.imageview);

            name.setText(animalsList.get(position).getNamr());
            Glide.with(context)
                    .load(animalsList.get(position).getLink()).into(imageView);




            return view;
        }
    }
}