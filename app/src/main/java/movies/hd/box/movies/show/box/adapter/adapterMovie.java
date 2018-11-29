package movies.hd.box.movies.show.box.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import movies.hd.box.movies.show.box.R;
import movies.hd.box.movies.show.box.model.movie;

public class adapterMovie extends RecyclerView.Adapter<adapterMovie.MyViewHolder>{

    private Context mContext;
    private List<movie> repos;

    public void setRepos(List<movie> repos) {
        this.repos = repos;
    }

    public adapterMovie(Context mContext, List<movie> repos)
    {
        this.mContext = mContext;
        this.repos = repos;
    }

    @Override
    public adapterMovie.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_movie,viewGroup,false);
        return new adapterMovie.MyViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final adapterMovie.MyViewHolder viewHolder, int i)
    {
        viewHolder.repo_name.setText(repos.get(i).getName());

        Glide.with(mContext).load(repos.get(i).getPicUrl()).placeholder(R.drawable.load)
                .into(viewHolder.im);

    }
    @Override
    public int getItemCount()
    {
        return repos.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView repo_name;
        public ImageView im;
        public MyViewHolder(View view)
        {
            super(view);
            repo_name =  view.findViewById(R.id.tectView_news_title_news_update_adapter);
            im = view.findViewById(R.id.imageView_news_Update_adapter);
        }
    }


}


