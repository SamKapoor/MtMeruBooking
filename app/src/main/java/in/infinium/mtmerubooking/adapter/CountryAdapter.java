package in.infinium.mtmerubooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.infinium.mtmerubooking.R;
import in.infinium.mtmerubooking.model.Country;

/**
 * Created by admin on 8/21/2016.
 */
public class CountryAdapter extends BaseAdapter {

    private ArrayList<Country> countryArrayList;
    private Context context;

    public CountryAdapter(Context context, ArrayList<Country> countryArrayList) {
        this.context = context;
        this.countryArrayList = countryArrayList;

    }

    @Override
    public int getCount() {
        return countryArrayList.size();
    }

    @Override
    public Country getItem(int position) {
        return countryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        try {
            return Long.parseLong(countryArrayList.get(position).getId());
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.row_spinner_country, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvName.setText(getItem(position).getName());
        return convertView;
    }
}
