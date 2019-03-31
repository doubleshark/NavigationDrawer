package com.example.srk.navigationdrawer.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srk.navigationdrawer.Others.Fine_pojo;
import com.example.srk.navigationdrawer.R;

public class Fine_Viewholder_Adapter extends RecyclerView.ViewHolder {

    View view;
    CardView card_inner;
    //FrameLayout stoke_framelayout;

    String finevalue = "0";

    public Fine_Viewholder_Adapter(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        //stoke_framelayout = itemView.findViewById(R.id.stoke_framelayout);
        card_inner = itemView.findViewById(R.id.card1);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickListener.onItemClick(v,getAdapterPosition());

                int position = getAdapterPosition();

                // use for select the item
                if (Fine_pojo.selecter && !(Fine_pojo.selected.contains((Integer)position))) {

                    //stoke_framelayout.setBackgroundColor(Color.rgb(98, 0, 238));
                    card_inner.setCardBackgroundColor(Color.rgb(224, 214, 238));

                    Fine_pojo.selected.add((Integer) position);
                    Fine_pojo.fineamount += Integer.parseInt(finevalue);
                    Toast.makeText(v.getContext(), "" + Fine_pojo.fineamount, Toast.LENGTH_SHORT).show();

                }

                // use for unselect the item
                else if (Fine_pojo.selecter && Fine_pojo.selected.contains((Integer)position)){

                    //stoke_framelayout.setBackgroundColor(Color.parseColor("#00000000"));
                    card_inner.setCardBackgroundColor(Color.parseColor("#ffffff"));

                    Fine_pojo.selected.remove((Integer) position);
                    Fine_pojo.fineamount -= Integer.parseInt(finevalue);
                    Toast.makeText(v.getContext(), "" + Fine_pojo.fineamount, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(v.getContext(), "Press Long for selection", Toast.LENGTH_SHORT).show();
                }


                // this help to active or deactive the long press selectetion
                if (Fine_pojo.selected.isEmpty()) {
                    Fine_pojo.selecter = false;
                    Fine_pojo.fineamount = 0;
                }



            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mclickListener.onItemLongClick(v,getAdapterPosition());

                if (!Fine_pojo.selecter) {

                    //stoke_framelayout.setBackgroundColor(Color.rgb(98, 0, 238));
                    card_inner.setCardBackgroundColor(Color.rgb(224, 214, 238));

                    Toast.makeText(v.getContext(), "" + Fine_pojo.fineamount, Toast.LENGTH_SHORT).show();
                    Fine_pojo.selecter = true;
                }


                return false;
            }
        });
    }

    public void setvalue(String booktitle, String accno, String authorname, String booktype, String fine) {

        TextView booktitle_tv = itemView.findViewById(R.id.booktitle_tv);
        TextView accno_tv = itemView.findViewById(R.id.accno_tv);
        TextView authorname_tv = itemView.findViewById(R.id.authorname_tv);
        TextView booktype_tv = itemView.findViewById(R.id.booktype_tv);
        TextView fine_tv = itemView.findViewById(R.id.fine_tv);

        booktitle_tv.setText(booktitle);
        accno_tv.setText(accno);
        authorname_tv.setText(authorname);
        booktype_tv.setText(booktype);
        fine_tv.setText(fine+" ₹");

        finevalue = fine;

    }

    private Fine_Viewholder_Adapter.clickListener mclickListener;
    public interface clickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(Fine_Viewholder_Adapter.clickListener clicklistener){
        mclickListener=clicklistener;
    }


}
