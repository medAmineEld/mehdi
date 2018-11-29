package movies.hd.box.movies.show.box;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import movies.hd.box.movies.show.box.Jsp.getMovies;
import movies.hd.box.movies.show.box.adapter.adapterMovie;
import movies.hd.box.movies.show.box.model.movie;

public class MainActivity extends AppCompatActivity {


    int page=1;
    List<movie> lstRepo = new ArrayList<>();
    adapterMovie adapterRepo = null;
    RecyclerView recyclerView;
    @Override
    protected void onStart() {
        super.onStart();
        getMovies();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    void initViews()
    {
        adapterRepo = new adapterMovie(this,lstRepo);
        recyclerView = findViewById(R.id.recycler_view_movie);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    page+=1;
                    getMovies();
                }
            }
        });
    }
    void getMovies()
    {
        (new getMovies(lstRepo,adapterRepo,recyclerView,page,this))
                .execute("https://egy.best/movies/horror?page="+page+"&output_mode=movies_list");
    }
}
