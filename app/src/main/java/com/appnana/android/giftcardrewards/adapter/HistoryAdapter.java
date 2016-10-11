package com.appnana.android.giftcardrewards.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appnana.android.giftcardrewards.R;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNanaHistory.Offer;
import com.appnana.android.offerwall.model.AppNanaHistory.Offer.Status;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<Offer> history;
    private Map<String, AbstractOffer> offerMap;

    public HistoryAdapter(Context context, List<Offer> history, Map<String, AbstractOffer> offerMap) {
        this.context = context;
        this.history = history;
        if (offerMap == null) {
            this.offerMap = new HashMap();
        } else {
            this.offerMap = offerMap;
        }
    }

    public int getCount() {
        return this.history.size();
    }

    public Offer getItem(int position) {
        Offer offer = (Offer) this.history.get(position);
        String uniqueId = offer.getUniqueId();
        if (offer.getStatus() == Status.Pending && this.offerMap.containsKey(uniqueId)) {
            offer.update((AbstractOffer) this.offerMap.get(uniqueId));
        }
        return offer;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public boolean isEnabled(int position) {
        return ((Offer) this.history.get(position)).getStatus() == Status.Pending;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.widget_history_item, parent, false);
        }
        ImageView imgIcon = (ImageView) ViewHolder.get(convertView, com.appnana.android.offerwall.R.id.img_icon);
        TextView txtNanas = (TextView) ViewHolder.get(convertView, com.appnana.android.offerwall.R.id.txt_nanas);
        TextView txtName = (TextView) ViewHolder.get(convertView, com.appnana.android.offerwall.R.id.txt_name);
        TextView txtStatus = (TextView) ViewHolder.get(convertView, R.id.txt_status);
        TextView txtDatetime = (TextView) ViewHolder.get(convertView, R.id.txt_datetime);
        Offer offer = getItem(position);
        if (offer.getStatus() == Status.Pending) {
            txtStatus.setTextColor(this.context.getResources().getColorStateList(R.color.color_green_white));
        } else {
            txtStatus.setTextColor(txtDatetime.getTextColors());
        }
        Picasso.with(this.context).load(offer.getIconUrl()).placeholder((int) com.appnana.android.offerwall.R.drawable.bkgd_loading_offer).into(imgIcon);
        txtNanas.setText(offer.getFormattedNanas());
        txtName.setText(offer.getName());
        txtStatus.setText(offer.getStatus().name());
        txtDatetime.setText(DateUtils.getRelativeTimeSpanString(offer.getCreateTime().getTime()));
        return convertView;
    }
}
