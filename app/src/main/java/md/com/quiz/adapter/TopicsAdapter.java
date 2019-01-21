package md.com.quiz.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import md.com.quiz.R;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicCustomViewHolder> {
    ArrayList<String> mArrayList;
    Context mContext;
    ArrayList<GradientDrawable> gradientDrawables=new ArrayList<>();
    public TopicsAdapter(Context context, ArrayList<String> arrayList)
    {
        mArrayList=arrayList;
        mContext=context;
        fillGradientList(mContext);
    }
    public void addTopic(String topic)
    {
        mArrayList.add(topic);
    }
    public void fillGradientList(Context context)
    {
        gradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_1_start),ContextCompat.getColor(context,R.color.gradient_1_end)));
        gradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_2_start),ContextCompat.getColor(context,R.color.gradient_2_end)));
        gradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_3_start),ContextCompat.getColor(context,R.color.gradient_3_end)));
        gradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_4_start),ContextCompat.getColor(context,R.color.gradient_4_end)));

    }

    private GradientDrawable getTempGradientDrawable(int startcolor, int endcolor) {
        GradientDrawable gradientDrawable=new GradientDrawable(GradientDrawable.Orientation.BL_TR,new int[]{startcolor, endcolor});
        gradientDrawable.setDither(true);
        gradientDrawable.setGradientCenter(gradientDrawable.getIntrinsicWidth()/8,gradientDrawable.getIntrinsicHeight()/2);
        gradientDrawable.setCornerRadius(20);
        gradientDrawable.setShape(gradientDrawable.RECTANGLE);
        gradientDrawable.setGradientType(gradientDrawable.LINEAR_GRADIENT);
        gradientDrawable.setUseLevel(true);
        return gradientDrawable;
    }


    @NonNull
    @Override
    public TopicCustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_item_list,viewGroup,false);
        return new TopicCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicCustomViewHolder topicCustomViewHolder, int i) {
        String topicname=mArrayList.get(i);
        topicCustomViewHolder.textView.setText(topicname);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TopicCustomViewHolder.relativeLayout.setBackground(gradientDrawables.get(i%4));
        }
        else
        {
            TopicCustomViewHolder.relativeLayout.setBackgroundDrawable(gradientDrawables.get(i%4));
        }
        topicCustomViewHolder.imageView.setImageResource(R.drawable.ic_menu);
    }
    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    static class TopicCustomViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        static RelativeLayout relativeLayout;
        public TopicCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.topicsImageView);
            textView=itemView.findViewById(R.id.topicsTextView);
            relativeLayout=itemView.findViewById(R.id.relative_layout);
        }
    }
}

