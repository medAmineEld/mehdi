package movies.hd.box.movies.show.box.Jsp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import movies.hd.box.movies.show.box.adapter.adapterMovie;
import movies.hd.box.movies.show.box.model.movie;

public class getMovies extends AsyncTask<String, Void, String> {

    List<movie> lstRepo;
    adapterMovie adapterRepo;
    RecyclerView recyclerView;
    Context con;
    int page;
    ProgressDialog dialog;
    public getMovies(List<movie> lstRepo,adapterMovie adapterRepo,RecyclerView recyclerView,int page,Context con)
    {
        this.page=page;this.lstRepo=lstRepo;this.adapterRepo=adapterRepo;this.recyclerView=recyclerView;
        this.con=con;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Document document = Jsoup.connect(params[0]).get();
            Element content = document.getElementById("movies");
            Elements movies = content.getElementsByTag("a");
            for(Element mo : movies)
                this.lstRepo.add(new movie(mo.getElementsByClass("title").text(),mo.getElementsByTag("img").attr("src")));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
        // might want to change "executed" for the returned string passed
        // into onPostExecute() but that is upto you
        if(page == 1)
        {
            adapterRepo.setRepos(lstRepo);
            recyclerView.setAdapter(adapterRepo);
            recyclerView.smoothScrollToPosition(0);
        }else
        {
            adapterRepo.notifyDataSetChanged();
        }
        dismissDialog();
    }

    @Override
    protected void onPreExecute() {showDialog();}

    @Override
    protected void onProgressUpdate(Void... values) {}
    private void showDialog()
    {
        dialog = new ProgressDialog(con);
        dialog.setMessage("Wait Please...");
        dialog.setCancelable(false);
        dialog.show();
    }
    void dismissDialog()
    {
        try {
            if(dialog!=null && dialog.isShowing())
                dialog.hide();
        }catch (Exception ex){}
    }
}
